package com.github.jotask.rosjam.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.State;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.util.Ref;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * GameStateManager
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class GameStateManager extends State{

    public enum STATE { SPLASH, MENU, GAME };

    private State currentState;

    public GameStateManager() {
        this.changeState(Ref.INITIAL_STATE);
    }

    @Override
    public void init() { }

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

        State s = null;
        switch (state){
            case GAME:
                s = new Game();
                break;
            default:
                throw new NotImplementedException();
        }

        currentState = s;
        currentState.init();

    }

    @Override
    public void resize(int width, int height) {
        this.currentState.resize(width, height);
    }
}

