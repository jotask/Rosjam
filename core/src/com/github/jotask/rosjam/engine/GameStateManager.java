package com.github.jotask.rosjam.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.rosjam.engine.states.AbstractState;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.game.Factory;
import com.github.jotask.rosjam.util.Ref;

/**
 * GameStateManager
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class GameStateManager extends AbstractState implements Disposable{

    public enum STATE { SPLASH, MENU, GAME, TEST }

    private CameraState currentState;

    public GameStateManager() {
        this.changeState(Ref.INITIAL_STATE);
    }

    @Override
    public void update() {
        currentState.preUpdate();
        currentState.update();
        currentState.postUpdate();
    }

    @Override
    public void render(SpriteBatch sb){
        OrthographicCamera camera = currentState.getCamera();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        currentState.preRender(sb);
        sb.end();
        sb.begin();
        currentState.render(sb);
        sb.end();
        sb.begin();
        currentState.postRender(sb);
        sb.end();
    }

    @Override
    public void debug(ShapeRenderer sr){
        OrthographicCamera camera = currentState.getCamera();
        sr.setProjectionMatrix(camera.combined);
        sr.begin();
        currentState.preDebug(sr);
        sr.end();
        sr.begin();
        currentState.debug(sr);
        sr.end();
        sr.begin();
        currentState.postDebug(sr);
        sr.end();
    }

    @Override
    public void dispose(){
        currentState.dispose();
    }

    public void changeState(STATE state){
        if(currentState != null)
            currentState.dispose();

        // TODO implement loading screen

        currentState = Factory.States.getState(state);

    }

    public void resize(int width, int height) { this.currentState.resize(width, height); }

    public CameraState getState() { return this.currentState;  }

}

