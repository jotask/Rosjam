package com.github.jotask.rosjam.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.rosjam.engine.states.AbstractState;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.factory.Factory;

/**
 * GameStateManager
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class GameStateManager extends AbstractState implements Disposable{

    public enum STATE { SPLASH, MENU, GAME, TEST, EDITOR, OPTIONS, NEAT, NEATOPTIONS }

    private CameraState currentState;

    public GameStateManager() { }

    @Override
    public void update() {
        this.currentState.preUpdate();
        this.currentState.update();
        this.currentState.postUpdate();
    }

    @Override
    public void render(SpriteBatch sb){
        final Color c = this.currentState.getColor();
        Gdx.gl20.glClearColor(c.r, c.g, c.b, c.a);
        OrthographicCamera camera = this.currentState.getCamera();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        this.currentState.preRender(sb);
        sb.end();
        sb.begin();
        this.currentState.render(sb);
        sb.end();
        sb.begin();
        this.currentState.postRender(sb);
        sb.end();
    }

    @Override
    public void debug(ShapeRenderer sr){
        OrthographicCamera camera = this.currentState.getCamera();
        sr.setProjectionMatrix(camera.combined);
        sr.begin();
        this.currentState.preDebug(sr);
        sr.end();
        sr.begin();
        this.currentState.debug(sr);
        sr.end();
        sr.begin();
        this.currentState.postDebug(sr);
        sr.end();
    }

    @Override
    public void dispose(){
        if(this.currentState != null)
            this.currentState.dispose();
    }

    public void changeState(STATE state){
        if(this.currentState != null)
            this.currentState.dispose();
        this.currentState = Factory.getState(state);
    }

    public void resize(int width, int height) { this.currentState.resize(width, height); }

    public CameraState getState() { return this.currentState;  }

}

