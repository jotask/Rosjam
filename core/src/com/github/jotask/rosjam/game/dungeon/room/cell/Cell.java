package com.github.jotask.rosjam.game.dungeon.room.cell;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * Cell
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class Cell {

    public Vector2 position;

    public TextureRegion region;

    private Rectangle bounds;

    public Cell(float x, float y, final TextureRegion region) {
        this.position = new Vector2(x, y);
        this.region = region;

        this.bounds = new Rectangle();
        this.bounds.setPosition(x, y);
        this.bounds.setSize(Room.CELL_SIZE, Room.CELL_SIZE);
    }

    public void render(final SpriteBatch sb){
        sb.draw(region, position.x, position.y, Room.CELL_SIZE, Room.CELL_SIZE);
    }

    public void debug(final ShapeRenderer sr){
        sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

}
