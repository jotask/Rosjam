package com.github.jotask.rosjam.factory;

import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.FollowCamera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.game.Game;

/**
 * StateBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class StateFactory {

    public static final CameraState getState(GameStateManager.STATE state){

        switch (state){
            case GAME:
                return getGameState();
            default:
                throw new RuntimeException("State not implemented");

        }

    }

    private static final Game getGameState(){
        FollowCamera camera = new FollowCamera();
        Game gs = new Game(camera);
        gs.init();
        camera.setTarget(gs.getGameManager().getPlayer());
        return gs;
    }

}
