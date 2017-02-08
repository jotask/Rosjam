package com.github.jotask.rosjam.game.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * Map
 *
 * @author Jose Vives Iznardo
 * @since 03/02/2017
 */
public class Map extends Image {

    public static final float WIDTH = 100;
    public static final float HEIGHT = 100;

    private Dungeon dungeon;

    public void setDungeon(com.github.jotask.rosjam.game.dungeon.Dungeon dungeon){
        this.dungeon = new Dungeon(dungeon);
        this.setDrawable(this.dungeon);
        this.setVisible(true);
    }

}

class Dungeon implements Drawable{

    private float leftWidth = 100;
    private float rightWidth = 50;

    private float topHeight = 50;
    private float bottomHeight = 50;

    private float minWidth = 50;
    private float minHeight = 50;

    private final com.github.jotask.rosjam.game.dungeon.Dungeon dungeon;
    private final Texture texture;

    public Dungeon(com.github.jotask.rosjam.game.dungeon.Dungeon dungeon) {
        this.dungeon = dungeon;

        Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGB565);
        pm.setColor(Color.WHITE);
        pm.drawRectangle(0,0,100, 100);
        this.texture = new Texture(pm);

    }

    @Override
    public void draw(Batch sb, float x, float y, float width, float height) {
        for(Room r: dungeon.getRooms())
            drawRoom(r, sb, x, y, width, height);
    }

    private void drawRoom(final Room r, Batch sb, float x, float y, float width, float height){

        final float offset = 1f;
        if(!r.isEntered()) {
            return;
        }else if(r.isInside()){
            sb.setColor(Color.RED);
        }else{
            sb.setColor(Color.BLACK);
        }
        sb.draw(texture, x+ r.bounds.x + offset, y + r.bounds.y + offset, Room.WIDTH - offset, Room.HEIGHT - offset);
        for(Door d: r.doors){
            sb.draw(texture, d.getPosition().x * Room.WIDTH, r.bounds.y + d.getPosition().y, 1f, 1f);
        }
    }

    @Override
    public float getLeftWidth() { return leftWidth; }

    @Override
    public void setLeftWidth(float leftWidth) { this.leftWidth = leftWidth; }

    @Override
    public float getRightWidth() { return rightWidth; }

    @Override
    public void setRightWidth(float rightWidth) { this.rightWidth = rightWidth; }

    @Override
    public float getTopHeight() { return this.topHeight; }

    @Override
    public void setTopHeight(float topHeight) { this.topHeight = topHeight; }

    @Override
    public float getBottomHeight() { return this.bottomHeight; }

    @Override
    public void setBottomHeight(float bottomHeight) { this.bottomHeight = bottomHeight; }

    @Override
    public float getMinWidth() { return this.minWidth; }

    @Override
    public void setMinWidth(float minWidth) { this.minWidth = minWidth; }

    @Override
    public float getMinHeight() { return this.minHeight; }

    @Override
    public void setMinHeight(float minHeight) { this.minHeight = minHeight; }

}
