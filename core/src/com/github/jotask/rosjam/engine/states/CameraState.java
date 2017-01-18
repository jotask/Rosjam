package com.github.jotask.rosjam.engine.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.camera.Camera;

/**
 * CameraState
 *
 * @author Jose Vives Iznardo
 * @since 16/01/2017
 */
public class CameraState extends State {

    protected final Camera camera;

    public CameraState(final Camera camera) {
        this.camera = camera;
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

    public void resize(int width, int height){ this.getCamera().resize(width, height); }

    public Camera getCamera() { return camera; }

}