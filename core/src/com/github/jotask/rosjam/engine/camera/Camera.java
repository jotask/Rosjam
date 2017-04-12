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

    static final int Z = 10;

    private final Viewport viewport;

    public Camera(){
        this(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Camera(final float w, final float h) {
        super(w, h);
        this.position.set(0, 0, Z);

        this.viewport = new FitViewport(w, h, this);
        this.viewport.apply();

    }

    public void resize(int width, int height){
        viewport.update(width, height);
        viewport.apply();
    }

    public Viewport getViewport(){ return this.viewport; }

}
