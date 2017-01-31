package com.github.jotask.rosjam.engine.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Camera
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Camera extends OrthographicCamera {

    protected final int Z = 10;

    public Viewport viewport;

    public Camera() {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.position.set(0, 0, Z);
        this.update();

//        float w = 21f;
//        float h = 11f;

        float w = Gdx.graphics.getWidth() / 10;
        float h = Gdx.graphics.getHeight() / 10;

        this.viewport = new FitViewport(w, h, this);
        this.viewport.apply();

    }

    public void resize(int width, int height){
        viewport.update(width, height);
        viewport.apply();
    }

    public void _update(){};

}
