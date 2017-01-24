package com.github.jotask.rosjam.engine.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.entity.Entity;

import java.text.DecimalFormat;

/**
 * Debug
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public class Debug extends Entity{

    private OrthographicCamera camera;

    private BitmapFont font;

    private enum DEBUG{

        FPS ("FPS: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f -13f * 0),
        ENTITY ("e: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 13f * 1),
        BODY ("b: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 13f * 2),
        PLAYER ("p: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 13f * 3);

        final String string;
        final float x, y;

        DEBUG(String string, float x, float y) {
            this.string = string;
            this.x = x;
            this.y = y;
        }
    }

    public Debug() {
        this.font = Rosjam.get().getAssets().getFont();
        font.setColor(Color.RED);
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void update() { }

    @Override
    public void render(SpriteBatch sb) {
        sb.end();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        float delta = Gdx.graphics.getDeltaTime();
        delta *= 1000;
        draw(sb, String.valueOf((int) delta), DEBUG.FPS);

        int entity = EntityManager.get().getSize();
        draw(sb, String.valueOf(entity), DEBUG.ENTITY);

        int body = DungeonState.get().getWorldManager().getWorld().getBodyCount();
        draw(sb, String.valueOf(body), DEBUG.BODY);


        Vector2 p = DungeonState.get().getPlayer().getBody().getPosition();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String x = df.format(p.x);
        String y = df.format(p.y);
        draw(sb, (x + " : " + y), DEBUG.PLAYER);

    }

    @Override
    public void debug(ShapeRenderer sr) {
    }

    private void draw(SpriteBatch sb, String text, DEBUG debug){

        float x =+ debug.x;
        float y =+ debug.y;

        font.draw(sb, debug.string + text, x, y);
    }

}
