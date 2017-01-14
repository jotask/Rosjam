package com.github.jotask.rosjam.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.Entity;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.LinkedList;

/**
 * Dungeon
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Dungeon extends Entity {

    private LinkedList<Room> rooms;

    public Room initialRoom;

    public Dungeon(LinkedList<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void update() {
        for(Room r: rooms){
            r.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for(Room r: rooms){
            r.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        for(Room r: rooms){
            r.debug(sr);
        }
    }
}
