package com.github.jotask.rosjam.game.hud;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.game.Game;

/**
 * Hud
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Hud{

    private final Game game;
    private final Stage stage;

    private final Application.ApplicationType type;

    public Hud(final Game game) {
        this.game = game;

        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        this.stage = new Stage(viewport, Rosjam.get().getSb());

        this.type = Gdx.app.getType();

        //Only if is in android
        if(type == Application.ApplicationType.Android) {
            Gdx.input.setInputProcessor(this.stage);
        }

    }

    public void update(final float delta) {
        this.stage.act(delta);
    }

    public void render(SpriteBatch sb) {
        sb.end();
        final Color c = sb.getColor();
        c.a = .5f;
        sb.setColor(c);
        this.stage.draw();
        c.a = 1f;
        sb.setColor(c);
        sb.begin();
    }

    public void dispose() {
        this.stage.dispose();
    }

    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
    }

    public void addControl(final Actor actor){ this.stage.addActor(actor); }

    public Stage getStage() { return this.stage; }

}
