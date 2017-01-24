package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
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

    private Color color;

    public boolean open;

    public SIDE side;

    public Room self;
    public Door connected;

    public Door(final Room self, final SIDE side) {
        this.self = self;
        this.side = side;
        open = false;

        color = randomColor();

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    private Color randomColor(){
        float r = MathUtils.random(0f, 1f);
        float g = MathUtils.random(0f, 1f);
        float b = MathUtils.random(0f, 1f);
        return new Color(r, g, b, 1f);
    }

    @Override
    public void debug(ShapeRenderer sr) {

        sr.setColor(color);

        Vector2 a = self.getCenter();
        Vector2 b = connected.self.getCenter();

        float x1 = a.x;
        float y1 = a.y;

        {
            switch (side){
                case UP:
                    y1 -= 5.72f;
                    break;
                case DOWN:
                    y1 += 5.72f;
                    break;
                case LEFT:
                    x1 -= 10.92f;
                    break;
                case RIGHT:
                    x1 += 10.92f;
                    break;
            }
        }

        float x2 = b.x;
        float y2 = b.y;

        {
            switch (connected.side){
                case UP:
                    y2 -= 5.72f;
                    break;
                case DOWN:
                    y2 += 5.72f;
                    break;
                case LEFT:
                    x2 -= 10.92f;
                    break;
                case RIGHT:
                    x2 += 10.92f;
                    break;
            }
        }

        sr.line(x1,y1, x2, y2);
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

    public SIDE getSide() { return side; }
}
