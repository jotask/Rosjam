package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.map.MapTiled;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.entity.Enemy;
import com.github.jotask.rosjam.game.entity.Entity;

import java.util.LinkedList;

/**
 * Room
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Room extends Entity {

    public static final int WIDTH = 21;
    public static final int HEIGHT = 11;

    public static final float CELL_SIZE = 1f;

    private Vector2 position;
    private MapTiled map;

    private Body walls;

//    private boolean[][] ai;

    public final Rectangle bounds;

    public final LinkedList<Door> doors;

    public final LinkedList<Vector2> spawners;

    public Room(final Vector2 position, final MapTiled map, final Rectangle bounds) {
        this.position = position;
        this.map = map;
        this.bounds = bounds;

        this.spawners = new LinkedList<Vector2>();

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

//        {
//            ai = new boolean[WIDTH][HEIGHT];
//            for(int i = 0; i < ai.length; i++){
//                for(int j = 0; j < ai[0].length; j++){
//                    ai[i][j] = false;
//                }
//            }
//        }
//
//        ai[7][7] = true;

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        this.map.render();
    }

    @Override
    public void debug(ShapeRenderer sr) {

//        sr.end();
//        sr.begin();
//        sr.set(ShapeRenderer.ShapeType.Filled);
//        sr.setColor(Color.BLACK);
//
//        float s = 1.04f;
//        for(int i = 0; i < ai.length; i++){
//            for(int j = 0; j < ai[0].length; j++){
//                sr.setColor(Color.BLACK);
//                if(ai[i][j])
//                    sr.setColor(Color.WHITE);
//                float x = i * s;
//                float y = j * s;
//                sr.rect(x , y, s, s);
//
//                if(ai[i][j]) {
//                    sr.setColor(Color.LIME);
//                    float s2 = s / 2f;
//                    float s22 = s2 / 2f;
//                    sr.rect(x + s2 - (s22), y + s2 - s22, s22, s22);
//                }
//
//            }
//        }

    }

    public Vector2 getCenter() {

        MapProperties prop = map.getMap().getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        float x  = ((mapWidth  * tilePixelWidth ) / 2f) * MapTiled.SCALE;
        float y  = ((mapHeight * tilePixelHeight) / 2f) * MapTiled.SCALE;

        x += position.x;
        y += position.y;

        return new Vector2(x, y);

    }

    public Vector2 getPosition() { return position; }

    public void setWalls(Body body){ this.walls = body; }

    public Body getWalls() { return walls; }

    public void enter(){

        for(Vector2 p: spawners) {
            Enemy enemy = EntityFactory.createEnemy(p);
            EntityManager.add(enemy);
        }

    }

}
