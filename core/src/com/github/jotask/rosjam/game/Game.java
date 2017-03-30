package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.engine.input.InputController;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.game.hud.Hud;
import com.github.jotask.rosjam.neat.NeatThread;

/**
 * Game
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class Game extends CameraState {

    enum GAMESTATES{ PLAY, PAUSE, GAMEOVER }

    private static Game instance;
    public static Game get(){
        return instance;
    }

    private final GameState play;
    private final GameState pause;
    private final GameState gameover;

    private GAMESTATES currentState = GAMESTATES.PLAY;

    private InputController controller;
    private Hud hud;

    private Thread thread;
    private NeatThread neatThread;

    public Game(Camera camera) {
        super(camera);
        Game.instance = this;

        this.controller = new InputController(this);

        this.play  = new DungeonState(this);
        this.pause = new PauseState(this);
        this.gameover = new GameOverState(this);

        this.hud = new Hud(this);

        this.neatThread = new NeatThread();
        this.thread = new Thread(this.neatThread);
        this.thread.start();

    }

    @Override
    public void preUpdate() {
        switch (this.currentState){
            case PLAY:
                this.play.preUpdate();
                break;
            case PAUSE:
                this.pause.preUpdate();
                break;
            case GAMEOVER:
                this.gameover.preUpdate();
                break;
        }
    }

    @Override
    public void postUpdate() {
        switch (this.currentState){
            case PLAY:
                this.play.postUpdate();
                break;
            case PAUSE:
                this.pause.postUpdate();
                break;
            case GAMEOVER:
                this.gameover.postUpdate();
                break;
        }
        this.getCamera()._update();
    }

    @Override
    public void preRender(SpriteBatch sb) {
        switch (this.currentState){
            case PLAY:
                this.play.preRender(sb);
                break;
            case PAUSE:
                this.pause.preRender(sb);
                break;
            case GAMEOVER:
                this.gameover.preRender(sb);
                break;
        }
    }

    @Override
    public void postRender(SpriteBatch sb) {
        switch (this.currentState){
            case PLAY:
                this.play.postRender(sb);
                break;
            case PAUSE:
                this.pause.postRender(sb);
                break;
            case GAMEOVER:
                this.gameover.postRender(sb);
                break;
        }
        this.hud.render(sb);
    }

    @Override
    public void preDebug(ShapeRenderer sr) {
        switch (this.currentState){
            case PLAY:
                this.play.preDebug(sr);
                break;
            case PAUSE:
                this.pause.preDebug(sr);
                break;
            case GAMEOVER:
                this.gameover.preDebug(sr);
                break;
        }
    }

    @Override
    public void postDebug(ShapeRenderer sr) {
        switch (this.currentState){
            case PLAY:
                this.play.postDebug(sr);
                break;
            case PAUSE:
                this.pause.postDebug(sr);
                break;
            case GAMEOVER:
                this.gameover.postDebug(sr);
                break;
        }
    }

    @Override
    public void update() {
        switch (this.currentState){
            case PLAY:
                this.play.update();
                break;
            case PAUSE:
                this.pause.update();
                break;
            case GAMEOVER:
                this.gameover.update();
                break;
        }
        this.hud.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch sb) {
        switch (this.currentState){
            case PLAY:
                this.play.render(sb);
                break;
            case PAUSE:
                this.pause.render(sb);
                break;
            case GAMEOVER:
                this.gameover.render(sb);
                break;
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        switch (this.currentState){
            case PLAY:
                this.play.debug(sr);
                break;
            case PAUSE:
                this.pause.debug(sr);
                break;
            case GAMEOVER:
                this.gameover.debug(sr);
                break;
        }
    }

    @Override
    public void dispose() {
        this.gameover.dispose();
        this.pause.dispose();
        this.gameover.dispose();
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

    public void changeState(final GAMESTATES gs){ this.currentState = gs; }

}
