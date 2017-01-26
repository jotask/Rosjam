package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.Entity;

/**
 * Door
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Door extends Entity {

    public enum SIDE {LEFT, UP, RIGHT, DOWN }

    public boolean open;

    public final Room self;
    public Door connected;

    public final SIDE side;

    public final Vector2 position;

    public Door(final Room self, final SIDE side) {
        this.self = self;
        this.side = side;
        this.open = false;

        this.position = new Vector2(self.getPosition());

    }

    @Override
    public void update() { }

    @Override
    public void render(SpriteBatch sb) { }

    @Override
    public void debug(ShapeRenderer sr) { }

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

    public SIDE getSide() { return side; }

    public boolean isOpen() { return open; }

    public void setOpen(boolean open){ this.open = open; }

}
