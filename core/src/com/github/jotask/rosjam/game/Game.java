package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.engine.Camera;
import com.github.jotask.rosjam.engine.GameManager;
import com.github.jotask.rosjam.engine.states.State;

/**
 * Game
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class Game extends State {

    private GameManager gameManager;

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float w = 21f;
        float h = 11f;
        this.camera.viewport = new FitViewport(w, h, camera);
        this.camera.viewport.apply();
        this.gameManager = new GameManager(this);
    }

    @Override
    public void preUpdate() { this.gameManager.preUpdate(); }

    @Override
    public void postUpdate() { this.gameManager.postUpdate(); }

    @Override
    public void preRender(SpriteBatch sb) { this.gameManager.preRender(sb); }

    @Override
    public void postRender(SpriteBatch sb) { this.gameManager.postRender(sb); }

    @Override
    public void preDebug(ShapeRenderer sr) { this.gameManager.preDebug(sr); }

    @Override
    public void postDebug(ShapeRenderer sr) { this.gameManager.postDebug(sr); }

    @Override
    public void update() {
        this.gameManager.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.gameManager.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        this.gameManager.debug(sr);
    }

    @Override
    public void dispose() {
        this.gameManager.dispose();
    }

}
