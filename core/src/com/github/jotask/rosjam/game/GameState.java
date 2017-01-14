package com.github.jotask.rosjam.game;

import com.github.jotask.rosjam.engine.states.AbstractState;

/**
 * GameState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class GameState extends AbstractState {

    protected final Game game;

    public GameState(Game game) {
        this.game = game;
    }

}
