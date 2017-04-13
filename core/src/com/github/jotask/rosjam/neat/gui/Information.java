package com.github.jotask.rosjam.neat.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.neat.Neat;
import com.github.jotask.rosjam.neat.NeatState;
import com.github.jotask.rosjam.neat.jneat.util.Util;

import java.util.LinkedList;

import static com.github.jotask.rosjam.neat.gui.EngineGui.OFFSET;
import static com.github.jotask.rosjam.neat.gui.EngineGui.SPACE;

/**
 * Information
 *
 * @author Jose Vives Iznardo
 * @since 03/03/2017
 */
public class Information implements Renderer {

    static abstract class Inform<T>{
        final BitmapFont font;
        final float x;
        final float y;
        final GlyphLayout glyph;
        final String text;
        String tmp;
        T t;

        Inform(LinkedList<Inform> info, final BitmapFont font, float x, float y, String text) {
            info.add(this);
            this.font = font;
            this.x = x;
            this.y = y;
            this.text = text;
            this.glyph = new GlyphLayout();
        }

        abstract void set();

        void render(final SpriteBatch sb){
            font.draw(sb, tmp, x - glyph.width, y);
        }

        void update(){
            this.tmp = this.text + ": " + t.toString();
            this.glyph.setText(font, tmp);
        }
    }

    private LinkedList<Inform> info;

    private final Neat neat;

    Information(final NeatState neatState) {
        this.info = new LinkedList<Inform>();

        this.neat = neatState.getNeat();

        final OrthographicCamera c = neatState.getEngineGui().getCamera();

        float x = c.position.x + (c.viewportWidth * .5f);
        float y = c.position.y + (c.viewportHeight * .5f);
        x -= OFFSET;
        y -= OFFSET;
        int i = 0;

        final BitmapFont font = Rosjam.get().getAssets().getFont();

        new Inform<Integer>(this.info, font, x, y - SPACE * i++, "Generation") {
            @Override
            void set() { this.t = neat.getJota().getPop().getGeneration(); }
        };

        new Inform<Integer>(this.info, font, x, y - SPACE * i++, "Species") {
            @Override
            void set() { this.t = neat.getJota().getPopulationSize(); }
        };

        new Inform<Integer>(this.info, font, x, y - SPACE * i++, "Active") {
            @Override
            void set() { this.t = neat.getJota().getManager().manyActived(); }
        };

        new Inform<Integer>(this.info, font, x, y - SPACE * i++, "Disabled") {
            @Override
            void set() { this.t = neat.getJota().getManager().manyDisabled(); }
        };

        new Inform<Double>(this.info, font, x, y - SPACE * i++, "MaxFitness" ) {
            @Override
            void set() { this.t = Util.limitDecimals(neat.getJota().getPop().maxFitness, 2); }
        };

    }

    @Override
    public void render(SpriteBatch sb) {
        for(final Inform i: info){
            i.set();
            i.update();
            i.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr) { }

}
