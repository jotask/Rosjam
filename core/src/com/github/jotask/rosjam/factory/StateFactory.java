package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.Gdx;
import com.github.jotask.rosjam.editor.EditorState;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.camera.RoomCamera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.menu.Menu;
import com.github.jotask.rosjam.neat.NeatState;
import com.github.jotask.rosjam.option.NeatOptions;
import com.github.jotask.rosjam.option.Options;
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
            case OPTIONS:
                return getOptionsState();
            case NEATOPTIONS:
                return getNeatOptionsState();
            case NEAT:
                return getNeatState();
            default:
                throw new RuntimeException("State not implemented");

        }

    }

    private static final NeatState getNeatState(){
        float w = Gdx.graphics.getWidth()  / 20f;
        float h = Gdx.graphics.getHeight() / 20f;
        final Camera camera = new Camera(w, h);
        NeatState ns = new NeatState(camera);
        return ns;
    }

    private static final Game getGameState(){
        // FIXME
        RoomCamera camera = new RoomCamera(21f, 11f);
        Game gs = new Game(camera);
        return gs;
    }

    private static final Options getOptionsState(){
        Camera camera = new Camera();
        Options option = new Options(camera);
        return option;
    }

    private static final NeatOptions getNeatOptionsState(){
        Camera camera = new Camera();
        NeatOptions option = new NeatOptions(camera);
        return option;
    }

    private static final Menu getMenuState(){
        Camera camera = new Camera();
        Menu menu = new Menu(camera);
        return menu;
    }

    private static final TestState getTestState(){
        Camera camera = new Camera(400, 200);
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
