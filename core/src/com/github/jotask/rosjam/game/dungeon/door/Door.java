package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.entity.Entity;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * Door
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Door extends Entity {

    public enum SIDE {LEFT, UP, RIGHT, DOWN }

    public boolean open;

    public SIDE side;

    public Room self;
    public Room connected;

    public Door(final Room self, final SIDE side) {
        this.self = self;
        this.side = side;
        open = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    public static SIDE getOpposite(SIDE side){
        switch (side){
            case LEFT:
                return SIDE.RIGHT;
            case RIGHT:
                return SIDE.LEFT;
            case UP:
                return SIDE.DOWN;
            case DOWN:
                return SIDE.UP;
            default:
                throw new RuntimeException("Exception in opposites rooms");
        }
    }

}
