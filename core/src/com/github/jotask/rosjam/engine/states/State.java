package com.github.jotask.rosjam.engine.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.Camera;

/**
 * State
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public abstract class State {

    protected Camera camera;

    public abstract void init();

    public void preUpdate(){}
    public abstract void update();
    public void postUpdate(){}

    public void preRender(SpriteBatch sb){}
    public abstract void render(SpriteBatch sb);
    public void postRender(SpriteBatch sb){}

    public void preDebug(ShapeRenderer sr){}
    public abstract void debug(ShapeRenderer sr);
    public void postDebug(ShapeRenderer sr){}

    public abstract void dispose();

    public Camera getCamera(){ return this.camera; }

    public void resize(int width, int height) { this.camera.resize(width, height);}

}
