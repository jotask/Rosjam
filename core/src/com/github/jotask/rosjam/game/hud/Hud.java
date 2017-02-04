package com.github.jotask.rosjam.game.hud;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.jotask.rosjam.Rosjam;
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
    private final Skin skin;
    private final Stage stage;
    private Table table;

    private final Map map;

    private final Application.ApplicationType type;

    public Hud(final Game game) {
        this.game = game;

        this.skin = Rosjam.get().getAssets().getSkin();

        this.stage = new Stage();
        this.table = new Table();
        this.table.setFillParent(true);
        this.table.debug();
        this.stage.addActor(table);

        this.map = new Map();
//        this.map.top().right();
        map.debug();
        float x = stage.getWidth() - Map.WIDTH;
        float y = stage.getHeight() - Map.HEIGHT;
        this.map.setPosition(x, y);
        this.table.addActor(map);

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
        this.stage.draw();
        if(type == Application.ApplicationType.Android)
            this.controls.render();
    }

    public void dispose() {
        this.stage.dispose();
        if(type == Application.ApplicationType.Android)
            this.controls.dispose();
    }

    public void resize(int width, int height) {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        this.stage.getViewport().update(w, h, true);

        if(type == Application.ApplicationType.Android)this.controls.resize(w, h);

    }

    public void addControl(final Actor actor){ this.controls.addActor(actor); }

    public Stage getStage() {
        return stage;
    }

    public Map getMap() { return map; }

}
