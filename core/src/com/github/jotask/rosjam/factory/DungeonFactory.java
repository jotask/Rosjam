package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.dungeon.config.ConfigRoom;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.util.CollisionFilter;

import java.util.LinkedList;

/**
 * DungeonBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class DungeonFactory {

    public static Dungeon generateDungeon(final ConfigDungeon configDungeon){

        Dungeon dungeon = generateDungeonValid(configDungeon);

        if(!dungeonChecker(dungeon)){
            throw new RuntimeException("Room is not valid");
        }

        return dungeon;

    }

    private static Dungeon generateDungeonValid(final ConfigDungeon configDungeon){

        configDungeon.worldManager.deleteDungeon();

        // FIXME the dungeon generator always generate rooms bottom and right. The problem starts when I added the code
        // to check if was a room occupied before spawn the next rooms. Maybe the error comes from there

        LinkedList<Room> rooms = new LinkedList<Room>();

        ConfigRoom cfg = new ConfigRoom(configDungeon.worldManager);

        Room initialRoom = RoomFactory.generateRoom(cfg);
        rooms.add(initialRoom);

        generator: while(rooms.size() < configDungeon.maxRooms){

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
                nextRoom.x += 1f;
                nextRoom.y += 1f;

                // Check if in that position exist a room
                if(isOccupied(rooms, nextRoom)){
                    continue generator;
                }

                nextRoom.x -= 1f;
                nextRoom.y -= 1f;

                ConfigRoom configRoom = new ConfigRoom(configDungeon.worldManager);
                configRoom.position = nextRoom;

                Room newRoom = RoomFactory.generateRoom(configRoom);

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

                    a.connected = b;
                    b.connected = a;

                }

                rooms.add(newRoom);
            }

        }

        Dungeon dungeon = new Dungeon(rooms);
        dungeon.initialRoom = initialRoom;

        return DungeonFactory.cleanDungeon(dungeon);

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

    private static Dungeon cleanDungeon(final Dungeon dungeon){

        // For each Room
        for(Room r: dungeon.getRooms()){
            // Handle doors
            doors(r);
        }

        return dungeon;

    }

    private static void doors(final Room room){
        final LinkedList<Door> doors = room.doors;
        for(int i = doors.size() - 1; i >= 0; i--){
            Door d = doors.get(i);
            if(d.connected != null){
                door(room, d);
            }else{
                doors.remove(i);
            }
        }
    }

    private static void door(final Room room, final Door door){

        Body body = room.getWalls();

        final Vector2 position = new Vector2();
        final Vector2 size = new Vector2(.5f, .5f);
        float angle = 0;

        switch (door.side){
            case LEFT:
                position.x += 2f * 1f;
                position.y += 6f * 1f;
                break;
            case UP:
                position.x += 11f * 1f;
                position.y += 2f * 1f;
                break;
            case RIGHT:
                position.x += 20f * 1f;
                position.y += 6f * 1f;
                break;
            case DOWN:
                position.x += 11f * 1f;
                position.y += 10f * 1f;
                position.y -= .25f;
                break;
        }

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x, size.y, position, angle);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.isSensor = true;

        CollisionFilter.setMask(fd, CollisionFilter.EENTITY.DOOR);

        Fixture fix = body.createFixture(fd);
        fix.setUserData(door);

        door.position.add(position);

        shape.dispose();

    }

    private static boolean dungeonChecker(final Dungeon dungeon){
        boolean isValid = true;
        for(Room r: dungeon.getRooms()){
            if(!roomChecker(r)){
                return false;
            }
        }
        return isValid;
    }

    private static boolean roomChecker(final Room room){
        boolean isValid = true;
        for(Door d: room.doors){
            if(!doorChecker(d)){
                return false;
            }
        }
        return isValid;
    }

    private static boolean doorChecker(final Door door){
        boolean isValid = true;
        if(door.position == null){
            return false;
        }else if(door == null){
            return false;
        }
        return isValid;
    }

}
