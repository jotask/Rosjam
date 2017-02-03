package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * Door
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Door {

    public static final float ANIMATION_SPEED = 0.025f;

    public enum SIDE{ UP, RIGHT, DOWN, LEFT }

    private Animation<TextureRegion> animation;
    private Vector2 position;
    public Door.SIDE side;

    public boolean isEntered;

    public final Room self;
    public Door connected;

    private float state;

    private boolean opened;

    public Door(Vector2 position, final Door.SIDE side, final Room room, Animation<TextureRegion> animation) {
        this.position = position;
        this.side = side;
        this.self = room;
        this.animation = animation;
        this.animation.setFrameDuration(.25f);
        this.animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        this.opened = true;
        this.isEntered = false;
    }

    public void render(SpriteBatch sb){

        state += Gdx.graphics.getDeltaTime();
        TextureRegion region = animation.getKeyFrame(state, true);

        sb.draw(region, position.x, position.y, 1f, 1f);
    }

    public void debug(ShapeRenderer sr) {
        sr.rect(position.x, position.y, 1f, 1f);
    }

    public void open(){
        opened = true;
    }
    public void close(){
        opened = false;
    }

    public boolean isOpen() {
        return opened;
    }
    public void setOpen(boolean opn){ this.opened = opn; }

    public Door.SIDE getOpposite(){
        Door.SIDE side = this.side;
        switch (side){
            case LEFT:
                return Door.SIDE.RIGHT;
            case RIGHT:
                return Door.SIDE.LEFT;
            case UP:
                return Door.SIDE.DOWN;
            case DOWN:
                return Door.SIDE.UP;
            default:
                throw new RuntimeException("Exception in opposites rooms");
        }
    }

    public Vector2 getPosition() { return position; }

}
