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
public abstract class ControlEntity extends HealthEntity{

    private final float SPEED = 7f;

    private final Controller controller;

    protected ControlEntity(Body body, final Controller controller, int health) {
        super(body, health);
        this.controller = controller;
    }

    @Override
    public void update() {

        final Vector2 dir = this.controller.getDirection();
        float speed = SPEED;
        float acceleration = 10f;
        float desiredVelocityX = Math.max(body.getLinearVelocity().x - acceleration,
                Math.min(dir.x * speed, body.getLinearVelocity().x + acceleration)
        );
        float desiredVelocityY = Math.max(body.getLinearVelocity().y - acceleration,
                Math.min(dir.y * speed, body.getLinearVelocity().y + acceleration)
        );

        final Vector2 momentum = body.getLinearVelocity();

        float velocityChangeX = desiredVelocityX - momentum.x;
        float velocityChangeY = desiredVelocityY - momentum.y;

        float impulseX = body.getMass() * velocityChangeX;
        float impulseY = body.getMass() * velocityChangeY;

        final Vector2 impulse = new Vector2(impulseX, impulseY);

        final Vector2 center = body.getWorldCenter();

        body.applyLinearImpulse(impulse, center, true);

    }

    public Controller getController() { return controller; }

}
