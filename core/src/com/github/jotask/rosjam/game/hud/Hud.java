package com.github.jotask.rosjam.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.states.AbstractState;
import com.github.jotask.rosjam.game.Game;

/**
 * Hud
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Hud extends AbstractState{

    private final Game game;
    private final Stage stage;
    private Table table;

    public Hud(final Game game) {
        this.game = game;

        final SpriteBatch sb = Rosjam.get().getSb();
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), sb);
        this.stage.setDebugAll(true);

        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
    }

    @Override
    public void update() {
        stage.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.end();
        stage.draw();
        sb.begin();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }

    public Stage getStage() { return stage; }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void addActor(final Actor actor){
        this.table.addActor(actor);
    }

}
