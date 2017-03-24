package com.github.jotask.rosjam.engine.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * AbstractState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public abstract class AbstractState implements IState {

    @Override
    public void preUpdate() {

    }

    @Override
    public void postUpdate() {

    }

    @Override
    public void preRender(SpriteBatch sb) {

    }

    @Override
    public void postRender(SpriteBatch sb) {

    }

    @Override
    public void preDebug(ShapeRenderer sr) {

    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    @Override
    public void postDebug(ShapeRenderer sr) {

    }

    @Override
    public void dispose() {

    }

}
