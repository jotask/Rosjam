package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.item.Weapon;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Player
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Player extends ControlEntity {

    private Weapon weapon;

    private Sprite sprite;

    private Vector2 moveTo;

    public Player(Body body, Controller controller, final Sprite sprite) {
        super(body, controller);
        this.sprite = sprite;

        this.weapon = EntityFactory.getWeapon(this);

    }

    @Override
    public void update() {
        super.update();

        if(moveTo != null){
            body.setTransform(moveTo, 0);
            moveTo = null;
        }

        if(getController().isShooting()){
            weapon.shot(getController().getShootDirection());
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sprite.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    public void reset(final Room room){
        this.getBody().setTransform(room.getCenter(), this.getBody().getAngle());
    }

    public void goTo(Door door){
        moveTo = door.connected.position;
    }

}
