package com.github.jotask.rosjam.game;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.Room;

import java.util.LinkedList;

/**
 * Factory
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Factory {

    public static Room generateRoom(float x, float y){
        Vector2 position = new Vector2(x, y);
        Room room = new Room(position);
        return room;
    }

    public static Dungeon generateDungeon(){

        LinkedList<Room> rooms = new LinkedList<Room>();
        Room room = generateRoom(0,0);
        rooms.add(room);

        Dungeon dungeon = new Dungeon(rooms);
        return dungeon;
    }

}
