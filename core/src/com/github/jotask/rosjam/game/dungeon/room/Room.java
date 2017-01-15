package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.Camera;
import com.github.jotask.rosjam.engine.map.MapTiled;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.entity.Entity;

import java.util.LinkedList;

/**
 * Room
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Room extends Entity {

    private final float SCALE = .065f;

    public static final int WIDTH = 21;
    public static final int HEIGHT = 11;

    public static final float CELL_SIZE = 1f;

    private Camera camera;
    private Vector2 position;
    private MapTiled map;

    public final Rectangle bounds;

    public final LinkedList<Door> doors;

    public Room(final Camera camera, final Vector2 position, final TiledMap map) {
        this.camera = camera;
        this.position = position;
        this.map = new MapTiled(position, map);

        this.bounds = new Rectangle();
        this.bounds.setPosition(position);

        MapProperties prop = this.map.getMap().getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        float x  = ((mapWidth  * tilePixelWidth )) * SCALE;
        float y  = ((mapHeight * tilePixelHeight)) * SCALE;

        this.bounds.setSize(x, y);

        {

            this.doors = new LinkedList<Door>();

            // Horizontal
//            layout[WIDTH - WALL_SIZE][HEIGHT / 2] = CELL_TYPE.DOOR;
            Door right = new Door(this, Door.SIDE.RIGHT);
            doors.add(right);

//            layout[WALL_SIZE - 1]    [HEIGHT / 2] = CELL_TYPE.DOOR;
            Door left = new Door(this, Door.SIDE.LEFT);
            doors.add(left);

            // Vertical
//            layout[WIDTH / 2][ WALL_SIZE - 1]     = CELL_TYPE.DOOR;
            Door down = new Door(this, Door.SIDE.DOWN);
            doors.add(down);

//            layout[WIDTH / 2][HEIGHT - WALL_SIZE] = CELL_TYPE.DOOR;
            Door up = new Door(this, Door.SIDE.UP);
            doors.add(up);

        }

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        this.map.render(camera);
    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    public Vector2 getCenter() {

        MapProperties prop = map.getMap().getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        float x  = ((mapWidth  * tilePixelWidth ) / 2f) * SCALE;
        float y  = ((mapHeight * tilePixelHeight) / 2f) * SCALE;

        x += position.x;
        y += position.y;

        return new Vector2(x, y);

    }

    public Vector2 getPosition() { return position; }
}
