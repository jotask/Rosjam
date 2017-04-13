package com.github.jotask.rosjam.neat.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.neat.NeatState;

/**
 * JotaGui
 *
 * @author Jose Vives Iznardo
 * @since 03/03/2017
 */
public class JotaGui implements Renderer {

    final NeatState jNeat;
    final OrthographicCamera camera;
    final BitmapFont font;

    private final Information information;
    private final Fitness fitness;

    public JotaGui(final NeatState jNeat) {
        this.jNeat = jNeat;
        this.font = Rosjam.get().getAssets().getFont();
        this.camera = jNeat.getEngineGui().getCamera();

        this.information = new Information(jNeat);
        this.fitness = new Fitness(this);

    }

    @Override
    public void render(final SpriteBatch sb) {
        this.information.render(sb);
        this.fitness.render(sb);
    }

    @Override
    public void debug(final ShapeRenderer sr) {
        this.information.debug(sr);
        this.fitness.debug(sr);
    }

    public Fitness getFitness() { return fitness; }

    public OrthographicCamera getCamera() { return camera; }

}