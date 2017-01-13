package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.State;

/**
 * Game
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class Game extends State {

    @Override
    public void init() {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    @Override
    public void dispose() {

    }

}
