package com.github.jotask.rosjam.engine.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.jotask.rosjam.game.entity.player.Player;

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

        float w = 21f;
        float h = 11f;

//        float w = Gdx.graphics.getWidth() / 5f;
//        float h = Gdx.graphics.getHeight() / 5f;

        this.viewport = new FitViewport(w, h, this);
        this.viewport.apply();

        instance = this;

    }

    public void resize(int width, int height){
        viewport.update(width, height);
        viewport.apply();
    }

    public void _update(){}

    public static void follow(Player player){
        float smooth = .5f;
        Camera.instance.position.lerp(new Vector3(player.getBody().getPosition(), Z), smooth);
        Camera.instance.update();
    }

    // TODO Dispose camera

}
