package com.github.jotask.rosjam.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.Entity;

/**
 * Door
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Door extends Entity {

    public enum SIDE {LEFT, UP, RIGHT, DOWN }

    private boolean open;

    private SIDE side;

    private Room self;
    private Room connected;

    public Door(Room self, SIDE side) {
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
