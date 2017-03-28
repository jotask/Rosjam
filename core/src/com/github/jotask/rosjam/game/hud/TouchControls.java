package com.github.jotask.rosjam.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * TouchControls
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class TouchControls {

    public Stage stage;
    private Table table;

    public TouchControls(final Stage stage, final Camera camera) {
        this.stage = new Stage(stage.getViewport(), stage.getBatch());
        this.table = new Table();
        this.table.setFillParent(true);

        this.stage.getViewport().setCamera(camera);
        this.stage.addActor(table);
        this.stage.setDebugAll(true);

        Gdx.input.setInputProcessor(this.stage);

    }

    public void update(final float delta){
        stage.act(delta);
    }

    public void render(){
        // FIXME

        //need to be improved
        stage.draw();
    }

    public void dispose(){
        this.stage.dispose();
    }

    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
    }

    public void addActor(Actor actor) {
        this.table.addActor(actor);
    }
}
