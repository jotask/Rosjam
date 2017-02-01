package com.github.jotask.rosjam.engine.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.jotask.rosjam.game.entity.Player;

/**
 * Camera
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Camera extends OrthographicCamera {

    public static final int Z = 10;

    private static Camera instance;

    public Viewport viewport;

    public Camera() {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.position.set(0, 0, Z);
        this.update();

//        float w = 21f;
//        float h = 11f;

        float w = Gdx.graphics.getWidth() / 20f;
        float h = Gdx.graphics.getHeight() / 20f;

        this.viewport = new FitViewport(w, h, this);
        this.viewport.apply();

        instance = this;

    }

    public void resize(int width, int height){
        viewport.update(width, height);
        viewport.apply();
    }

    public void _update(){};

    public static void follow(Player player){
        Camera.instance.position.set(player.getBody().getPosition(), Z);
        Camera.instance.update();
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO eliminate commentd
        System.out.println("camera finalized");
        super.finalize();
        instance = null;
    }
}
