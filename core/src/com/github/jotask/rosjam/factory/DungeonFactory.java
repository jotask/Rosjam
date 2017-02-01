package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.github.jotask.rosjam.editor.TileData;
import com.github.jotask.rosjam.engine.assets.DungeonAssets;
import com.github.jotask.rosjam.engine.assets.Tiles;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.LinkedList;

/**
 * DungeonBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class DungeonFactory {

    private final World world;

    private final DungeonAssets assets;

    public DungeonFactory(final World world, final DungeonAssets assets) {
        this.world = world;
        this.assets = assets;
    }

    public final Dungeon generateDungeon(ConfigDungeon cfg){

        LinkedList<Room> rooms = new LinkedList<Room>();

        Room initialRoom = room(null, new Vector2());
        rooms.add(initialRoom);

        generator: while(rooms.size() < cfg.maxRooms){

            // Choose a random room
            Room room;
            {
                boolean isValid;
                do {
                    int index = cfg.random.random(rooms.size());
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
                    int index = cfg.random.random(doors.size());
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

                Room newRoom = room(cfg, nextRoom);

                // Connect doors
                {

                    Door a = door;
                    Door b = null;
                    Door.SIDE opposite = a.getOpposite();
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

        dungeon = new Cleaner().cleanDungeon(dungeon);

        return dungeon;

    }

    private final Room room(final ConfigDungeon cfg, final Vector2 position){

        TextureRegion background = assets.getBackground();

        Room room = new Room(position, background);
        BodyFactory.createRoom(room);

        room.doors.add(getDoor(room, Door.SIDE.UP));
        room.doors.add(getDoor(room, Door.SIDE.RIGHT));
        room.doors.add(getDoor(room, Door.SIDE.DOWN));
        room.doors.add(getDoor(room, Door.SIDE.LEFT));

        if(cfg == null)
            return room;

        FileHandle dir = Gdx.files.internal("rooms");
        if(dir.list().length > 0) {

            int index = cfg.random.random(dir.list().length);
            FileHandle file = dir.list()[index];

            Json json = new Json();
            final LinkedList<TileData> tiles = json.fromJson(LinkedList.class, TileData.class, file);

            for (TileData t : tiles) {
                switch (t.tile) {
                    case WALL:
                    case EMPTY:
                        continue;
                    case ROCK:
                        spawnRock(room, t);
                        break;
                    case SPAWN:
                        spawner(room, t);
                        break;
                    default:
                        throw new RuntimeException("Not Supported");
                }
            }
        }

        return room;

    }

    private void spawnRock(final Room room, final TileData d) {
        // TODO create code to spawn rocks
    }

    private void spawner(final Room room, final TileData data){
        Rectangle r = room.getBounds();
        Vector2 v = new Vector2();
        v.x = r.x + data.x;
        v.y = r.y + data.y;
        room.spawner.add(v);
    }

    private Door getDoor(final Room room, Door.SIDE side){

        Vector2 p = new Vector2(room.getBody().getPosition());
        TextureRegion[] regions = new TextureRegion[4];

        // TODO Fix the door offset

        float w = 9.28f;
        float h = 4.28f;

        switch (side){
            case DOWN:
                p.y += h - .4f;
                p.x -= .5;
                regions[0] = assets.get(Tiles.DOOR_TOP_01);
                regions[1] = assets.get(Tiles.DOOR_TOP_02);
                regions[2] = assets.get(Tiles.DOOR_TOP_03);
                regions[3] = assets.get(Tiles.DOOR_TOP_04);
                break;
            case RIGHT:
                p.x += w - .4f;
                p.y -= .5f;
                regions[0] = assets.get(Tiles.DOOR_RIGHT_01);
                regions[1] = assets.get(Tiles.DOOR_RIGHT_02);
                regions[2] = assets.get(Tiles.DOOR_RIGHT_03);
                regions[3] = assets.get(Tiles.DOOR_RIGHT_04);
                break;
            case UP:
                p.y -= h + .5f;
                p.x -= .5;
                regions[0] = assets.get(Tiles.DOOR_BOTTOM_01);
                regions[1] = assets.get(Tiles.DOOR_BOTTOM_02);
                regions[2] = assets.get(Tiles.DOOR_BOTTOM_03);
                regions[3] = assets.get(Tiles.DOOR_BOTTOM_04);
                break;
            case LEFT:
                p.x -= w + .5f;
                p.y -= .5f;
                regions[0] = assets.get(Tiles.DOOR_LEFT_01);
                regions[1] = assets.get(Tiles.DOOR_LEFT_02);
                regions[2] = assets.get(Tiles.DOOR_LEFT_03);
                regions[3] = assets.get(Tiles.DOOR_LEFT_04);
                break;
            default:
                throw new RuntimeException("Unknown Door type");
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(Door.ANIMATION_SPEED, regions);

        Door door = new Door(p, side, room, animation);

        return door;

    }

    private void createEdgeFixture(Body body, Vector2 a, Vector2 b){
        EdgeShape shape = new EdgeShape();
        shape.set(a, b);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        body.createFixture(fd);
        shape.dispose();
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

        float x = room.bounds.x;
        float y = room.bounds.y;

        switch (door.side){
            case LEFT:
                x -= Room.WIDTH;
                break;
            case RIGHT:
                x += Room.WIDTH;
                break;
            case UP:
                y -= Room.HEIGHT;
                break;
            case DOWN:
                y += Room.HEIGHT;
                break;
        }

        return new Vector2(x, y);

    }

    private class Cleaner{

        public Dungeon cleanDungeon(final Dungeon dungeon){
            for(Room r: dungeon.getRooms()){
                cleanRoom(r);
            }

            spawnEnemies(dungeon);

            return dungeon;

        }

        private void spawnEnemies(Dungeon dungeon){
            for(Room room: dungeon.getRooms()){
                room.enter();
            }
        }

        private void cleanRoom(final Room room){
            final LinkedList<Door> doors = room.doors;
            for(int i = doors.size() - 1; i >= 0; i--){
                Door d = doors.get(i);
                if(d.connected == null){
                    doors.remove(i);
                }else{
                    BodyFactory.createDoor(d);
                }
            }

        }

    }

}
