package com.github.jotask.rosjam.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.input.controllers.GamePad;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.entity.Entity;

/**
 * InputController
 *
 * @author Jose Vives Iznardo
 * @since 16/01/2017
 */
public class InputController extends Entity {

    private final Game game;

    private Controller controller;

    public InputController(Game game) {
        this.game = game;
        switch (Gdx.app.getType()){
            case Android:
                this.controller = new AndroidController(game.getHud());
                break;
            case Desktop:
                final GamePad gamePad = new GamePad();
                if(gamePad.isSupported()){
                    this.controller = gamePad.getController();
                }else {
                    this.controller = new DesktopController();
                }
                break;
            default:
                throw new RuntimeException("System not supported");
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    public Controller getController() { return controller; }

}
