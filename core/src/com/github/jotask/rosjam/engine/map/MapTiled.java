package com.github.jotask.rosjam.engine.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

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
    private OrthographicCamera camera;

    private JotaMapRenderer renderer;

    public MapTiled(Vector2 position, final TiledMap map, final OrthographicCamera camera) {
        this.position = position;
        this.map = map;
        this.camera = camera;

        renderer = new JotaMapRenderer(this);

    }

    public TiledMap getMap() { return map; }

    public Vector2 getPosition() { return position; }

    public void render() {
        renderer.getBatch().end();
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
    }

}
