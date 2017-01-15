package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.DungeonAssets;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.cell.Cell;
import com.github.jotask.rosjam.game.entity.Entity;

import java.util.LinkedList;

/**
 * Room
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Room extends Entity {

    public enum CELL_TYPE {
        WALL (Color.RED),
        FLOOR(Color.WHITE);

        Color color;

        CELL_TYPE(Color color) {
            this.color = color;
        }
    }

    public static final int WIDTH = 21;
    public static final int HEIGHT = 11;

    public static final float CELL_SIZE = 1f;

    public CELL_TYPE[][] layout;

    public Cell[][] cells;

    private Vector2 position;

    public LinkedList<Door> doors;

    public Rectangle bounds;

    public Room(final Vector2 position) {

        this.position = position;

        this.bounds = new Rectangle();
        this.bounds.setPosition(this.position);
        this.bounds.setSize(WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);

        layout = new CELL_TYPE[WIDTH][HEIGHT];

        final int WALL_SIZE = 2;

        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){

                layout[i][j] = CELL_TYPE.FLOOR;

                if(i < WALL_SIZE | j < WALL_SIZE |
                        i >  WIDTH - 1 - WALL_SIZE |
                        j > HEIGHT - 1 - WALL_SIZE){
                    layout[i][j] = CELL_TYPE.WALL;
                }

            }
        }

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

        {
            cells = new Cell[WIDTH][HEIGHT];

            final DungeonAssets assets = Rosjam.get().getAssets().getDungeonAssets();
            for(int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[0].length; j++) {
                    TextureRegion region;

                    region = assets.getRegion(DungeonAssets.TILES.FLOOR);

//                    if(true){
//                        region = assets.getRegion(DungeonAssets.TILES.WALL_BOTTOM);
//                    }

                    cells[i][j] = new Cell(i * CELL_SIZE, j * CELL_SIZE, region);
                }
            }
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        for(int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                cells[i][j].render(sb);
            }
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {

        for(int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                cells[i][j].debug(sr);
            }
        }

//        sr.set(ShapeRenderer.ShapeType.Line);
//        for(int i = 0; i < layout.length; i++){
//            for(int j = 0; j < layout[0].length; j++) {
//                sr.setColor(layout[i][j].color);
//                sr.rect(position.x + (i * CELL_SIZE), position.y + (j * CELL_SIZE), CELL_SIZE, CELL_SIZE);
//            }
//        }
//        sr.set(ShapeRenderer.ShapeType.Line);
//        for(int i = 0; i < layout.length; i++){
//            for(int j = 0; j < layout[0].length; j++) {
//                sr.setColor(Color.BLACK);
//                sr.rect(position.x + (i * CELL_SIZE), position.y + (j * CELL_SIZE), CELL_SIZE, CELL_SIZE);
//            }
//        }

//        sr.setColor(Color.RED);
//        sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);

    }

    public Vector2 getPosition() { return position; }

    public Vector2 getCenter(){
        float x = position.x + (WIDTH  * CELL_SIZE) / 2f;
        float y = position.y + (HEIGHT * CELL_SIZE) / 2f;
        return new Vector2(x, y);
    }

}
