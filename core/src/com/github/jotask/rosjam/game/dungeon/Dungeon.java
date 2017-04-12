package com.github.jotask.rosjam.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.entity.Entity;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.LinkedList;

/**
 * Dungeon
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Dungeon extends Entity {

    public final ConfigDungeon cfg;

    private LinkedList<Room> rooms;

    public Room initialRoom;

    public Dungeon(final ConfigDungeon cfg, final LinkedList<Room> rooms) {
        this.cfg = cfg;
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

    public void exit(){
        World world = rooms.getFirst().getBody().getWorld();
        for(Room r: rooms){
            world.destroyBody(r.getBody());
        }
    }

    public LinkedList<Room> getRooms() { return rooms; }
}
