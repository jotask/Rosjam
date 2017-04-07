package com.github.jotask.rosjam.game.hud;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.game.Game;

/**
 * Hud
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Hud{

    private TouchControls controls;

    private Camera camera;

    private final Game game;
    private final Stage stage;

//    private final Map map;

    private final Application.ApplicationType type;

    public Hud(final Game game) {
        this.game = game;

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.stage = new Stage();
        this.stage.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        this.type = Gdx.app.getType();
        //Only if is in android
        if(type == Application.ApplicationType.Android) {
            this.controls = new TouchControls(this.stage, this.camera);
        }

    }

    public void update(final float delta) {
        this.stage.act(delta);
        if(type == Application.ApplicationType.Android)
            this.controls.update(delta);
    }

    public void render(SpriteBatch sb) {

        if(type == Application.ApplicationType.Android)
            this.controls.render();
    }

    public void dispose() {
        if(type == Application.ApplicationType.Android)
            this.controls.dispose();
    }

    public void resize(int width, int height) {

        this.stage.getViewport().update(width, height, true);

        if(type == Application.ApplicationType.Android)this.controls.resize(width, height);

    }

    public void addControl(final Actor actor){ this.controls.addActor(actor); }

    public Stage getStage() { return this.stage; }

//    public Map getMap() { return map; }

}
