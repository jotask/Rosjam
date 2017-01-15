package com.github.jotask.rosjam.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private Table table;

    private final float scale = 1f;

    public Hud(final Game game) {
        this.game = game;

        // FIXME
        // Change the viewport for different resolutions

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth() * scale,
                Gdx.graphics.getHeight() * scale);


        this.stage = new Stage();
        this.table = new Table();
        this.table.setFillParent(true);

        this.stage.getViewport().setCamera(this.camera);
        this.stage.addActor(table);
        this.stage.setDebugAll(true);
//        this.stage.getBatch().dispose();

//        final SpriteBatch sb = Rosjam.get().getSb();
//        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), sb);
//        this.stage.setDebugAll(true);

//        table = new Table();
//        table.setFillParent(true);
//        table.setDebug(true);
//        stage.addActor(table);

        this.controls = new TouchControls(this.stage, this.camera);

    }

    public void update(final float delta) {
        this.stage.act(delta);
        this.controls.update(delta);
    }

    public void render(SpriteBatch sb) {
        this.stage.draw();
        this.controls.render();
    }

    public void dispose() {
         this.stage.dispose();
         this.controls.dispose();
    }

    public void resize(int width, int height) {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        this.stage.getViewport().update(w, h, true);
        this.controls.resize(w, h);
    }

    public void addControl(final Actor actor){ this.controls.addActor(actor); }

}
