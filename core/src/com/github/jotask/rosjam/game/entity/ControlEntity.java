package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.input.Controller;

/**
 * ControlEntity
 *
 * @author Jose Vives Iznardo
 * @since 24/01/2017
 */
public class ControlEntity extends BodyEntity{

    private final float SPEED = 7f;

    private final Controller controller;

    protected ControlEntity(Body body, final Controller controller) {
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

    public Controller getController() { return controller; }

}
