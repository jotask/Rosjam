package com.github.jotask.rosjam.engine.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.game.entity.Entity;

/**
 * Debug
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public class Debug extends Entity{

    private OrthographicCamera camera;

    private BitmapFont font;

    private static int i = 0;

    private enum DEBUG{

        FPS ("FPS: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f -13f * i++),
        HEAPMEMORY ("JavaHeap: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 13f * i++),
        NATIVEMEMORY ("NativeHeap: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 13f * i++),
        ENTITY ("Entities: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 13f * i++),
        PLAYER ("Player: ", -Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 13f * i++);

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
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        this.font.setColor(Color.RED);

        float delta = Gdx.graphics.getDeltaTime();
        delta *= 1000;
        draw(sb, String.valueOf((int) delta), DEBUG.FPS);

        long javaHeap = Gdx.app.getJavaHeap();
        draw(sb, String.valueOf(toMB(javaHeap)) + "mb", DEBUG.HEAPMEMORY);

        long nativeHeap = Gdx.app.getNativeHeap();
        draw(sb, String.valueOf(toMB(nativeHeap)) + "mb", DEBUG.NATIVEMEMORY);

//        int entity = EntityManager.get().getSize();
//        draw(sb, String.valueOf(entity), DEBUG.ENTITY);

//        Vector2 p = EntityManager.get().getPlayer().getBody().getPosition();
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(2);
//        String x = df.format(p.x);
//        String y = df.format(p.y);
//        draw(sb, (x + " : " + y), DEBUG.PLAYER);

        sb.end();

    }

    @Override
    public void debug(ShapeRenderer sr) {
    }

    private void draw(SpriteBatch sb, String text, DEBUG debug){

        float x =+ debug.x;
        float y =+ debug.y;

        font.draw(sb, debug.string + text, x, y);
    }

    private long toMB(final long bytes){
        return bytes / (1024 * 1024);
    }

}
