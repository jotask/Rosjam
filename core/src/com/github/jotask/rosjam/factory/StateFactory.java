package com.github.jotask.rosjam.factory;

import com.github.jotask.rosjam.editor.EditorState;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.camera.RoomCamera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.menu.Menu;
import com.github.jotask.rosjam.splash.Splash;
import com.github.jotask.rosjam.test.TestState;

/**
 * StateBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class StateFactory {

    public static final CameraState getState(GameStateManager.STATE state) {

        switch (state) {
            case GAME:
                return getGameState();
            case TEST:
                return getTestState();
            case EDITOR:
                return getEditorState();
            case SPLASH:
                return getSplashState();
            case MENU:
                return getMenuState();
            default:
                throw new RuntimeException("State not implemented");

        }

    }

    private static final Game getGameState(){
        RoomCamera camera = new RoomCamera(21f, 11f);
        Game gs = new Game(camera);
        return gs;
    }

    private static final Menu getMenuState(){
        Camera camera = new Camera();
        Menu menu = new Menu(camera);
        return menu;
    }

    private static final TestState getTestState(){
        Camera camera = new Camera();
        TestState ts = new TestState(camera);
        return ts;
    }

    public static CameraState getEditorState() {
        Camera camera = new Camera();
        EditorState state = new EditorState(camera);
        return state;
    }

    public static CameraState getSplashState() {
        Camera camera = new Camera();
        Splash splash = new Splash(camera);
        return splash;
    }
}
