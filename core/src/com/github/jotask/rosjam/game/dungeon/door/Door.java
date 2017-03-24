package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.util.DoorSprite;

/**
 * Door
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Door {

    public enum SIDE{ UP, RIGHT, DOWN, LEFT }

    private DoorSprite sprite;
    private Vector2 position;

    public boolean isEntered;

    public final Room self;
    public Door connected;

    private boolean opened;

    public Door(Vector2 position, final Room room, final DoorSprite sprite) {
        this.position = position;
        this.self = room;
        this.sprite = sprite;
        this.opened = true;
        this.isEntered = false;
    }

    public void render(SpriteBatch sb){
        sprite.render(sb, this.getPosition());
    }

    public void debug(ShapeRenderer sr) {
        //sr.rect(position.x, position.y, 1f, 1f);
    }

    public boolean isOpen() {
        return opened;
    }
    public void setOpen(boolean opn){
        this.opened = opn;
        if(this.opened){
            this.sprite.open();
        }else{
            this.sprite.close();
        }
    }

    public void reset(){
        this.setOpen(false);
    }

    public Vector2 getPosition() { return position; }

}
