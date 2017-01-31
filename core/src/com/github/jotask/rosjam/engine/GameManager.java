package com.github.jotask.rosjam.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.engine.states.IState;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.Game;

/**
 * GameManager
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class GameManager extends GameState {

    public enum STATE { DUNGEON }

    private IState currentState;

    private Controller controller;

    public GameManager(final Game game, final Controller controller) {
        super(game);
        this.controller = controller;
        this.changeState(STATE.DUNGEON);
    }

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

        GameState s = null;
        switch (state){
            case DUNGEON:
                s = new DungeonState(this.game);
                break;
            default:
                throw new RuntimeException("State not implemented");
        }

        this.setPlayer(s.getPlayer());

        currentState = s;
        currentState.init();

    }

}
