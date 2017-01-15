package com.github.jotask.rosjam.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.DungeonTest;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.dungeon.room.TestRoom;
import com.github.jotask.rosjam.game.entity.Player;
import com.github.jotask.rosjam.test.TestState;

import java.util.LinkedList;

/**
 * Factory
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public final class Factory {

    private static final int MAX_ROOMS = 1;

    public static DungeonTest generateTestDungeon(final TestState state){

        LinkedList<TestRoom> rooms = new LinkedList<TestRoom>();

        TiledMap map = new TmxMapLoader().load("test.tmx");

        TestRoom room = new TestRoom(state, new Vector2(0,0), map);

        DungeonTest dungeon = new DungeonTest(rooms);
        dungeon.initialRoom = room;
        return dungeon;

    }

    public static Room generateRoom(final Vector2 position, final WorldManager worldManager) {
        Room room = new Room(position);

        LinkedList<Body> bodies = new LinkedList<Body>();
        final Room.CELL_TYPE[][] layout = room.layout;
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){
                if(layout[i][j] == Room.CELL_TYPE.WALL){
                    bodies.add(createBodyForRoom(worldManager.getWorld(), room, i, j));
                }
            }
        }

        return room;
    }

    private static Body createBodyForRoom(final World world, final Room room, int i, int j){

        final float SIZE = Room.CELL_SIZE / 2f;

        float x = room.getPosition().x + SIZE;
        float y = room.getPosition().y + SIZE;

        x += i;
        y += j;

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(x, y);

        Body body = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(SIZE, SIZE);

        body.createFixture(shape, 1f);

        shape.dispose();

        return body;
    }

    public static Dungeon generateDungeon(final WorldManager worldManager){

        worldManager.deleteAllBodies();

        // FIXME the dungeon generator always generate rooms bottom and right. The problem starts when I added the code
        // to check if was a room occupied before spawn the next rooms. Maybe the error comes from there

        LinkedList<Room> rooms = new LinkedList<Room>();
        Room initialRoom = generateRoom(new Vector2(0,0), worldManager);
        rooms.add(initialRoom);

        generator: while(rooms.size() < MAX_ROOMS){

            // Choose a random room
            Room room;
            {
                boolean isValid;
                do {
                    int index = MathUtils.random(rooms.size() - 1);
                    room = rooms.get(index);
                    isValid = true;
                } while (!isValid);
            }

            // Choose a random door not connected
            final LinkedList<Door> doors = room.doors;
            Door door;
            {
                boolean isValid = false;

                // Check if all doors are connected
                boolean areConnected = true;
                for(Door door1: doors){
                    if(door1.connected == null){
                        areConnected = false;
                        break;
                    }
                }
                if(areConnected){
                    continue generator;
                }

                do{
                    int index = MathUtils.random(doors.size() - 1);
                    door = doors.get(index);

                    if(door.connected == null) {
                        isValid = true;
                    }

                }while(!isValid);
            }

            // Spawn a room
            {
                Vector2 nextRoom = getNextRoom(room, door);
                // Check if in that position exist a room
                if(isOccupied(rooms, nextRoom)){
                    continue generator;
                }

                Room newRoom = generateRoom(nextRoom, worldManager);

                // Connect doors
                {
                    Door a = door;
                    Door b = null;
                    Door.SIDE opposite = Door.getOpposite(a.side);
                    for(Door ddd: newRoom.doors){
                        if(ddd.side == opposite){
                            b = ddd;
                            break;
                        }
                    }

                    a.connected = b.self;
                    b.connected = a.self;

                }

                rooms.add(newRoom);
            }

        }

        Dungeon dungeon = new Dungeon(rooms);
        dungeon.initialRoom = initialRoom;
        return dungeon;

    }

    private static boolean isOccupied(final LinkedList<Room> rooms, final Vector2 nextRoom){
        for(Room r: rooms){
            if(r.bounds.contains(nextRoom)) {
                return true;
            }
        }
        return false;
    }

    private static Vector2 getNextRoom(final Room room, final Door door){

        float x = room.getPosition().x;
        float y = room.getPosition().y;

        switch (door.side){
            case LEFT:
                x -= Room.WIDTH * Room.CELL_SIZE;
                break;
            case RIGHT:
                x += Room.WIDTH * Room.CELL_SIZE;
                break;
            case UP:
                y -= Room.HEIGHT * Room.CELL_SIZE;
                break;
            case DOWN:
                y += Room.HEIGHT * Room.CELL_SIZE;
                break;
        }

        return new Vector2(x, y);

    }

    public static Player generatePlayer(final WorldManager worldManager, final Room room) {

        final Vector2 center = room.getCenter();

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(center.x, center.y);

        Body body = worldManager.getWorld().createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.5f, .5f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        Player player = new Player(body);
        return player;
    }

}
