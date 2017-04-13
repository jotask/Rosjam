package com.github.jotask.rosjam.neat.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.neat.NeatState;
import com.github.jotask.rosjam.neat.util.Util;

/**
 * JotaGui
 *
 * @author Jose Vives Iznardo
 * @since 12/02/2017
 */
public class EngineGui {

    static final float SPACE = 15f;
    static final float OFFSET = 10f;

    private final NeatState neat;

    private final OrthographicCamera camera;

    private final BitmapFont font;

    public EngineGui(NeatState neat) {
        this.neat = neat;
        this.font = Rosjam.get().getAssets().getFont();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    }

    public void render(SpriteBatch sb){
        float x = camera.position.x - camera.viewportWidth / 2f;
        float y = camera.position.y + camera.viewportHeight / 2f;
        x += OFFSET;
        y -= OFFSET;
        int i = 0;
        font.draw(sb, "FPS: " + Gdx.graphics.getFramesPerSecond(), x, y - SPACE * i++);
        font.draw(sb, "JavaHeap: " + Util.bytesToMb(Gdx.app.getJavaHeap()), x, y - SPACE * i++);
        font.draw(sb, "NativeHeap: " + Util.bytesToMb(Gdx.app.getNativeHeap()), x, y - SPACE * i++);
    }

    public OrthographicCamera getCamera() { return camera; }

}
