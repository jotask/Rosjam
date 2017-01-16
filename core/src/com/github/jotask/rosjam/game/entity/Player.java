package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * Player
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Player extends BodyEntity {

    private final float SPEED = 10f;

    private final Controller controller;

    public Player(Body body, Controller controller) {
        super(body);
        this.controller = controller;
    }

    @Override
    public void update() {

        // TODO take a look to the following ling to better movement
        // http://www.box2d.org/forum/viewtopic.php?t=9756

        final Vector2 dir = controller.getDirection();
        dir.x *= SPEED;
        dir.y *= SPEED;


        body.setLinearVelocity(dir);

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    public void reset(final Room room){
        this.getBody().setTransform(room.getCenter(), this.getBody().getAngle());
    }

}
