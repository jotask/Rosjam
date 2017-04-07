package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Json;
import com.github.jotask.rosjam.editor.EditorState;
import com.github.jotask.rosjam.editor.TileData;
import com.github.jotask.rosjam.engine.assets.Tiles;
import com.github.jotask.rosjam.game.Spawner;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.door.NextLevelDoor;
import com.github.jotask.rosjam.game.dungeon.door.RoomDoor;
import com.github.jotask.rosjam.game.dungeon.room.BossRoom;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;
import com.github.jotask.rosjam.game.entity.obstacle.Rock;
import com.github.jotask.rosjam.util.DoorSprite;

import java.util.LinkedList;

/**
 * DungeonBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class DungeonFactory {

    public final Dungeon generateDungeon(ConfigDungeon cfg){

        LinkedList<Room> rooms = new LinkedList<Room>();

        Room initialRoom = room(cfg, new Vector2(), true);
        rooms.add(initialRoom);

        generator: while(rooms.size() < cfg.maxRooms){

            // Choose a random room
            Room room = chooseRandomRoom(cfg, rooms);

            // Choose a random door not connected
            RoomDoor door = chooseRandomDoor(cfg, room.doors);
            if(door == null) continue generator;

            // Spawn a room
            {
                Vector2 nextRoom = getNextRoom(room, door);
                nextRoom.x += 1f;
                nextRoom.y += 1f;

                // Check if in that start exist a room
                if(isOccupied(rooms, nextRoom)){
                    continue generator;
                }

                nextRoom.x -= 1f;
                nextRoom.y -= 1f;

                Room newRoom = room(cfg, nextRoom);

                // Connect doors
                connectRooms(door, newRoom);

                rooms.add(newRoom);

            }

        }

        specialRooms(cfg, rooms);

        Dungeon dungeon = new Dungeon(rooms);
        dungeon.initialRoom = initialRoom;

        dungeon = new Cleaner().cleanDungeon(dungeon);

        return dungeon;

    }

    private final void connectRooms(final RoomDoor door, final Room newRoom){

        RoomDoor a = door;
        RoomDoor b = null;
        Door.SIDE opposite = a.getOpposite();
        for(Door ddd: newRoom.doors){
            if(!(ddd instanceof RoomDoor)){
                continue;
            }
            RoomDoor rd = (RoomDoor) ddd;
            if(rd.side == opposite){
                b = rd;
                break;
            }
        }

        a.connected = b;
        b.connected = a;

    }

    private final Room chooseRandomRoom(final ConfigDungeon cfg, LinkedList<Room> rooms){

        Room room;

        boolean isValid;
        do {
            int index = cfg.random.random(rooms.size());
            room = rooms.get(index);
            isValid = true;
        } while (!isValid);

        return room;

    }

    private final RoomDoor chooseRandomDoor(final ConfigDungeon cfg, LinkedList<Door> doors){

        RoomDoor door = null;

        boolean isValid = false;

        // Check if all doors are connected
        boolean areConnected = true;
        for(Door door1: doors){

            if(!(door1 instanceof RoomDoor))
                continue;

            if(door1.connected == null){
                areConnected = false;
                break;
            }
        }

        // FIXME
        if(areConnected){
            return null;
        }

        do{
            int index = cfg.random.random(doors.size());
            Door rd = doors.get(index);

            if(!(rd instanceof RoomDoor)){
                continue;
            }

            door = (RoomDoor) rd;

            if(door.connected == null) {
                isValid = true;
            }

        }while(!isValid);

        return door;
    }

    private final void specialRooms(final ConfigDungeon cfg, final LinkedList<Room> rooms){
        // Get the most far away room
        final LinkedList<Room> fars = new Graph(rooms.getFirst()).getFars();
        TextureRegion region = cfg.dungeonAssets.getBackground();
        Vector2  pos = null;
        RoomDoor door = null;

        for(Room r: fars){

            // Choose a random door not connected
            door = chooseRandomDoor(cfg, r.doors);
            if(door == null) continue;

            // Spawn a room
            Vector2 nextRoom = getNextRoom(r, door);
            nextRoom.x += 1f;
            nextRoom.y += 1f;

            // Check if in that start exist a room
            if(isOccupied(rooms, nextRoom)){
                continue;
            }

            nextRoom.x -= 1f;
            nextRoom.y -= 1f;

            pos = nextRoom;

            break;

        }

        BossRoom bossRoom = new BossRoom(pos, region);
        BodyFactory.createRoom(bossRoom);

        bossRoom.doors.add(getDoor(bossRoom, Door.SIDE.UP));
        bossRoom.doors.add(getDoor(bossRoom, Door.SIDE.RIGHT));
        bossRoom.doors.add(getDoor(bossRoom, Door.SIDE.DOWN));
        bossRoom.doors.add(getDoor(bossRoom, Door.SIDE.LEFT));

        {

            Vector2 p = new Vector2(bossRoom.getCenter().sub(.5f , .5f));

            DoorSprite sprite = SpriteFactory.getFinalDoor();

            NextLevelDoor nld = new NextLevelDoor(p, bossRoom, sprite);
            bossRoom.doors.add(nld);

            BodyFactory.createDoor(nld);

        }


        connectRooms(door, bossRoom);

        rooms.add(bossRoom);

    }

    private final Room room(final ConfigDungeon cfg, final Vector2 position){
        return room(cfg, position, false);
    }

    private final Room room(final ConfigDungeon cfg, final Vector2 position, boolean initial){

        TextureRegion background = cfg.dungeonAssets.getBackground();

        Room room = new Room(position, background);
        BodyFactory.createRoom(room);

        room.doors.add(getDoor(room, Door.SIDE.UP));
        room.doors.add(getDoor(room, Door.SIDE.RIGHT));
        room.doors.add(getDoor(room, Door.SIDE.DOWN));
        room.doors.add(getDoor(room, Door.SIDE.LEFT));

        if(initial)
            return room;

        FileHandle dir = Gdx.files.internal("rooms");
        if(dir.list().length > 0) {

            int index = cfg.random.random(dir.list().length);
            FileHandle file = dir.list()[index];

            Json json = new Json();
            final LinkedList<TileData> tiles = json.fromJson(LinkedList.class, TileData.class, file);

            EditorState.Tile[][] layout = new EditorState.Tile[Room.WIDTH][Room.HEIGHT];
            for(TileData t: tiles){
                layout[t.x][t.y] = t.tile;
            }
            room.setLayout(layout);

            for (TileData t : tiles) {
                switch (t.tile) {
                    case WALL:
                    case EMPTY:
                        continue;
                    case ROCK:
                        spawnRock(cfg, room, t);
                        break;
                    case SPAWN:
                        spawner(cfg, room, t);
                        break;
                    default:
                        throw new RuntimeException("Not Supported");
                }
            }
        }

        return room;

    }

    private void spawnRock(final ConfigDungeon cfg, final Room room, final TileData data) {

        Vector2 pos = new Vector2( room.getBounds().x + data.x + .5f, room.getBounds().y + data.y + .5f);

        Body body = BodyFactory.createRock(cfg.worldManager.getWorld(), pos);

        final LinkedList<TextureRegion> regions = new LinkedList<TextureRegion>();
        regions.add(cfg.dungeonAssets.get(Tiles.ROCK_01));
        regions.add(cfg.dungeonAssets.get(Tiles.ROCK_02));

        int index = MathUtils.random(regions.size() - 1);

        Rock rock = new Rock(body, regions.get(index));

        room.entities.add(rock);

    }

    private void spawner(final ConfigDungeon cfg, final Room room, final TileData data){

        Rectangle r = room.getBounds();

        final Enemies enemy = cfg.random.getRandomEnemy();

        Vector2 v = new Vector2();
        v.x = r.x + data.x + .5f;
        v.y = r.y + data.y + .5f;

        final Spawner s = new Spawner(enemy, v);

        room.spawner.add(s);

    }

    private Door getDoor(final Room room, Door.SIDE side){

        Vector2 p = new Vector2(room.getBody().getPosition());

        float w = 9.28f;
        float h = 4.28f;

        switch (side){
            case DOWN:
                p.y += h - .4f;
                p.x -= .5;
                break;
            case RIGHT:
                p.x += w - .4f;
                p.y -= .5f;
                break;
            case UP:
                p.y -= h + .5f;
                p.x -= .5;
                break;
            case LEFT:
                p.x -= w + .5f;
                p.y -= .5f;
                break;
            default:
                throw new RuntimeException("Unknown Door type");
        }

        DoorSprite sprite = SpriteFactory.getDoor(side);

        Door door = new RoomDoor(p, side, room, sprite);

        return door;

    }

    private static boolean isOccupied(final LinkedList<Room> rooms, final Vector2 nextRoom){
        for(Room r: rooms){
            if(r.bounds.contains(nextRoom)) {
                return true;
            }
        }
        return false;
    }

    private static Vector2 getNextRoom(final Room room, final RoomDoor door){

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

            dungeon.initialRoom.setCompleted(true);

            return dungeon;

        }

        private void cleanRoom(final Room room){
            final LinkedList<Door> doors = room.doors;
            for(int i = doors.size() - 1; i >= 0; i--){
                Door d = doors.get(i);

                if(d instanceof NextLevelDoor){
                    continue;
                }

                if(d.connected == null){
                    doors.remove(i);
                }else{
                    BodyFactory.createDoor(d);
                }
            }

        }

    }

}
