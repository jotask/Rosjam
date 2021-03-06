package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.util.Timer;

/**
 * RandomWalker
 *
 * @author Jose Vives Iznardo
 * @since 01/02/2017
 */
public class RandomWalker extends ArtificialIntelligence {

    private final float SPEED = 50f;

    private Timer timer;

    public RandomWalker(Body body) {
        super(body);
        this.timer = new Timer(1f);
    }

    @Override
    public void update() {

        if(timer.isPassed(true)) {
            body.setLinearVelocity(Vector2.Zero);
            body.setAngularVelocity(0);
            randomMovement();
        }else{
            dir.setZero();
        }

        this.body.applyForceToCenter(dir.scl(SPEED), true);

    }

    private void randomMovement(){
        float x = MathUtils.random(-1, 1);
        float y = MathUtils.random(-1, 1);
        this.dir.add(x, y).nor();
    }

}
