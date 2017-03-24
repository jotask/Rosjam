package com.github.jotask.rosjam.engine.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * IState
 *
 * @author Jose Vives Iznardo
 * @since 16/01/2017
 */
public interface IState {

    void preUpdate();
    void update();
    void postUpdate();

    void preRender(SpriteBatch sb);
    void render(SpriteBatch sb);
    void postRender(SpriteBatch sb);

    void preDebug(ShapeRenderer sr);
    void debug(ShapeRenderer sr);
    void postDebug(ShapeRenderer sr);

    void dispose();

}
