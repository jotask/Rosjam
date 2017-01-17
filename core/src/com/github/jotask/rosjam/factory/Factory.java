package com.github.jotask.rosjam.factory;

import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.states.CameraState;

/**
 * Factory
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public final class Factory {

    public static CameraState getState(GameStateManager.STATE state){ return StateFactory.getState(state); }

}
