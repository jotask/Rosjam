package com.github.jotask.rosjam.editor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * TileActor
 *
 * @author Jose Vives Iznardo
 * @since 30/01/2017
 */
public class TileActor extends Actor{

    public static final float SIZE = 23f;

    public TileData tile;

    private boolean valid;

    private final EditorState editorState;

    public TileActor(EditorState editorState) {
        this.editorState = editorState;

        this.setDebug(true);

        this.tile = new TileData();

        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float xx, float yy) {
                if(valid) {
                    changeTile();
                }
            }
        });

    }

    private void changeTile() {
        final com.badlogic.gdx.scenes.scene2d.ui.CheckBox selected = editorState.menuEditor.checkBox.getSelected();
        String val = selected.getName();
        for(EditorState.Tile t: EditorState.Tile.values()){
            if(t.name().equals(val)){
                this.tile.tile = t;
                return;
            }
        }
    }

    public static TileActor build(EditorState editorState, int i, int j){
        TileActor t = new TileActor(editorState);
        int xx = (int) (i * SIZE);
        int yy = (int) (j * SIZE);
        t.tile.x = j;
        t.tile.y = i;
        t.setPosition(xx, yy);
        t.setSize(SIZE, SIZE);

        if(t.tile.x < 2 || t.tile.y < 2 || t.tile.x >= Room.WIDTH - 2 || t.tile.y >= Room.HEIGHT - 2){
            t.valid = false;
            t.tile.tile = EditorState.Tile.WALL;
        }else{
            t.valid = true;
            t.tile.tile = EditorState.Tile.EMPTY;
        }

        return t;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(tile == null){
            throw new RuntimeException("NUUUUUUUUUULLLLL");
        }
        batch.draw(tile.tile.texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

}
