package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.Entity;
import com.github.jotask.rosjam.game.dungeon.door.Door;

import java.util.LinkedList;

/**
 * Room
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Room extends Entity {

    enum CELL_TYPE {
        WALL (Color.RED),
        FLOOR(Color.WHITE);

        Color color;

        CELL_TYPE(Color color) {
            this.color = color;
        }
    }

    private int WIDTH = 21;
    private int HEIGHT = 11;

    private final float CELL_SIZE = 3f;

    private CELL_TYPE[][] layout;

    private Vector2 position;

    private LinkedList<Door> doors;

    public Room(final Vector2 position) {

        this.position = position;

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

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.set(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++) {
                sr.setColor(layout[i][j].color);
                sr.rect(position.x + (i * CELL_SIZE), position.y + (j * CELL_SIZE), CELL_SIZE, CELL_SIZE);
            }
        }
        sr.set(ShapeRenderer.ShapeType.Line);
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++) {
                sr.setColor(Color.BLACK);
                sr.rect(position.x + (i * CELL_SIZE), position.y + (j * CELL_SIZE), CELL_SIZE, CELL_SIZE);
            }
        }
    }

}
