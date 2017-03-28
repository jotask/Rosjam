package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.GameManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.debug.Debug;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.engine.input.InputController;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.game.hud.Hud;
import com.github.jotask.rosjam.neat.NeatThread;
import com.github.jotask.rosjam.util.Ref;

/**
 * Game
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class Game extends CameraState {

    private static Game instance;
    public static Game get(){
        return instance;
    }

    // TODO refactor this into rosjam
    private Debug debug;

    private GameManager gameManager;
    private InputController controller;
    private Hud hud;

    private Thread thread;
    private NeatThread neatThread;

    public Game(Camera camera) {
        super(camera);
        Game.instance = this;

        this.debug = new Debug();

        this.hud = new Hud(this);
        this.controller = new InputController(this);
        this.gameManager = new GameManager(this, this.getController());

        this.neatThread = new NeatThread();
        this.thread = new Thread(this.neatThread);
        this.thread.start();

    }

    @Override
    public void preUpdate() { this.gameManager.preUpdate(); }

    @Override
    public void postUpdate() {
        this.gameManager.postUpdate();
        this.getCamera()._update();
    }

    @Override
    public void preRender(SpriteBatch sb) { this.gameManager.preRender(sb); }

    @Override
    public void postRender(SpriteBatch sb) {
        this.gameManager.postRender(sb);
        this.hud.render(sb);
        if(Ref.APP_DEBUG) {
            this.debug.render(sb);
        }
    }

    @Override
    public void preDebug(ShapeRenderer sr) { this.gameManager.preDebug(sr); }

    @Override
    public void postDebug(ShapeRenderer sr) { this.gameManager.postDebug(sr); }

    @Override
    public void update() {
        this.gameManager.update();
        this.hud.update(Gdx.graphics.getDeltaTime());
        this.debug.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.gameManager.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        this.gameManager.debug(sr);
    }

    @Override
    public void dispose() {
        this.gameManager.dispose();
        this.hud.dispose();
        this.neatThread.stop();
        try {
            this.thread.join(3000);
        } catch (InterruptedException e) {
            System.err.println("Unable to close loop");
            e.printStackTrace();
        }
        this.instance = null;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.hud.resize(width, height);
    }

    public Controller getController() { return controller.getController(); }

    public Hud getHud() { return hud; }

    public GameManager getGameManager() { return gameManager; }



}
