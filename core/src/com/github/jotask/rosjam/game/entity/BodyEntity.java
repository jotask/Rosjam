package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * BodyEntity
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public abstract class BodyEntity extends Entity {

    private final Body body;

    protected BodyEntity(final Body body) {
        this.body = body;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

}
