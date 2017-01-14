package com.github.jotask.rosjam.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.LinkedList;

/**
 * Factory
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public final class Factory {

    private static final int MAX_ROOMS = 7;

    public static Room generateRoom(float x, float y){ return generateRoom(new Vector2(x, y)); }

    public static Room generateRoom(final Vector2 position) {
        Room room = new Room(position);
        return room;
    }

    public static Dungeon generateDungeon(){

        LinkedList<Room> rooms = new LinkedList<Room>();
        Room initialRoom = generateRoom(0, 0);
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

                Room newRoom = generateRoom(nextRoom);

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

}
