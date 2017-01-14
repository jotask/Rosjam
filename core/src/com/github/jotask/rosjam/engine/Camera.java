package com.github.jotask.rosjam.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Camera
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Camera extends OrthographicCamera {

    public Viewport viewport;

    public Camera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    public void resize(int width, int height){
        // TODO resize camera and viewport
    }

}
