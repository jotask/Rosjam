package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.game.entity.Entity;

/**
 * ArtificialIntelligence
 *
 * @author Jose Vives Iznardo
 * @since 23/01/2017
 */
public class ArtificialIntelligence extends Entity implements Controller {

    protected Body body;

    protected Vector2 dir;

    public ArtificialIntelligence(Body body) {
        this.body = body;
        this.dir = new Vector2(0, 0);
    }

    @Override
    public Vector2 getDirection() {
        return dir;
    }

    @Override
    public boolean isShooting() {
        return false;
    }

    @Override
    public Vector2 getShootDirection() {
        return null;
    }

    @Override
    public final boolean resetLevel() {
        return false;
    }

    @Override
    public void update() { }

    @Override
    public final void render(SpriteBatch sb) {}

}
