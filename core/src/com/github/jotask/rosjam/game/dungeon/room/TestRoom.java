package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.map.MapTiled;
import com.github.jotask.rosjam.game.entity.Entity;
import com.github.jotask.rosjam.test.TestState;

/**
 * TestRoo
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class TestRoom extends Entity{

    private final float scale = .065f;

    private final TestState state;

    private Vector2 position;
    private MapTiled map;

    private Rectangle bounds;

    public TestRoom(final TestState state, final Vector2 position, final TiledMap map) {
        this.state = state;
        this.position = position;
        this.map = new MapTiled(position, map);

        final float off = 0f;

        this.bounds = new Rectangle();
        this.bounds.setPosition(position.x + off, position.y + off);

        MapProperties prop = map.getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        float x  = ((mapWidth  * tilePixelWidth )) * scale;
        float y  = ((mapHeight * tilePixelHeight)) * scale;

        this.bounds.setSize(x - off * 2, y - off * 2);

    }

    @Override
    public void update() { }

    @Override
    public void render(SpriteBatch sb) {
        this.map.render(state.getCamera());
    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Vector2 getCenter() {

        MapProperties prop = map.getMap().getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        float x  = ((mapWidth  * tilePixelWidth ) / 2f) * scale;
        float y  = ((mapHeight * tilePixelHeight) / 2f) * scale;

        return new Vector2(x, y);

    }
}


