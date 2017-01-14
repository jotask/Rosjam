package com.github.jotask.rosjam.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.State;
import com.github.jotask.rosjam.game.DungeonState;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * GameManager
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class GameManager extends State{

    public enum STATE { DUNGEON }

    private State currentState;

    public GameManager() {
        this.changeState(STATE.DUNGEON);
    }

    @Override
    public void init() { }

    @Override
    public void preUpdate() { currentState.preUpdate(); }

    @Override
    public void postUpdate() { currentState.postUpdate(); }

    @Override
    public void preRender(SpriteBatch sb) { currentState.preRender(sb); }

    @Override
    public void postRender(SpriteBatch sb) { currentState.postRender(sb); }

    @Override
    public void preDebug(ShapeRenderer sr) { currentState.preDebug(sr); }

    @Override
    public void postDebug(ShapeRenderer sr) { currentState.postDebug(sr); }

    @Override
    public void update() { currentState.update(); }

    @Override
    public void render(SpriteBatch sb){ currentState.render(sb); }

    @Override
    public void debug(ShapeRenderer sr){ currentState.debug(sr); }

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
            case DUNGEON:
                s = new DungeonState();
                break;
            default:
                throw new NotImplementedException();
        }

        currentState = s;
        currentState.init();

    }

}
