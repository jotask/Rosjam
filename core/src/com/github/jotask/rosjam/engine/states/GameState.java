package com.github.jotask.rosjam.engine.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.Game;

/**
 * GameState
 *
 * @author Jose Vives Iznardo
 * @since 16/01/2017
 */
public abstract class GameState extends State {

    protected final Game game;

    public GameState(final Game game) {
        this.game = game;
    }

    @Override
    public void init() {

    }

    @Override
    public void preUpdate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void postUpdate() {

    }

    @Override
    public void preRender(SpriteBatch sb) {

    }

    @Override
    public void render(SpriteBatch sb) {

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

    public Game getGame() { return game; }

}
