package com.github.jotask.rosjam.engine.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.Camera;

/**
 * MapTiled
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class MapTiled {

    public static final float SCALE = 0.065f;

    private Vector2 position;
    private TiledMap map;

    private JotaMapRenderer renderer;

    public MapTiled(Vector2 position, final TiledMap map) {
        this.position = position;
        this.map = map;

        renderer = new JotaMapRenderer(this);
    }

    public TiledMap getMap() { return map; }

    public Vector2 getPosition() { return position; }

    public void render(Camera camera) {
        renderer.getBatch().end();
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
    }

}
