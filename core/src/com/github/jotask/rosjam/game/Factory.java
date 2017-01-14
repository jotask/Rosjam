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
        {
            Room initial = generateRoom(0, 0);
            rooms.add(initial);
        }

        while(rooms.size() < MAX_ROOMS){

            // Choose a random room
            Room room;
            {
                boolean isValid = false;
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
                do{
                    int index = MathUtils.random(doors.size() - 1);
                    door = doors.get(index);

                    if(door.connected == null)
                        isValid = true;

                }while(!isValid);
            }

            // Spawn a room
            Room newRoom = generateRoom(getNextRoom(room, door));
            rooms.add(newRoom);

        }

        System.out.println("Rooms: " + rooms.size());

        Dungeon dungeon = new Dungeon(rooms);
        return dungeon;
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
