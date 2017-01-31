package com.github.jotask.rosjam.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.LinkedList;

/**
 * EditorScreen
 *
 * @author Jose Vives Iznardo
 * @since 29/01/2017
 */
public class EditorScreen extends Table{

    private final TextureRegion region;
    private final EditorState editorState;

    private Vector2 position;

    private LinkedList<TileActor> tiles;

    public EditorScreen(EditorState editorState) {
        this.editorState = editorState;
        this.region = Rosjam.get().getAssets().getDungeonAssets().getBackground();
        this.setPosition(MenuEditor.WIDTH, 0);
        this.setSize(Gdx.graphics.getWidth() - MenuEditor.WIDTH, Gdx.graphics.getHeight());

        this.position = new Vector2(0,0);

        this.tiles = new LinkedList<TileActor>();

        for(int i = Room.HEIGHT - 1; i >= 0; i--){
            for(int j = 0; j < Room.WIDTH; j++){
                TileActor t = TileActor.build(editorState, i , j);
                tiles.add(t);
                this.add(t);
            }
            this.row();
        }

    }

    public void render(SpriteBatch sb){
        sb.draw(region, position.x, position.y);
    }

    public LinkedList<TileActor> getTiles() { return tiles; }

    public void reset() {
        for(TileActor t: tiles){
            if(t.tile.tile == EditorState.Tile.WALL)
                continue;
            t.tile.tile = EditorState.Tile.EMPTY;
        }
    }

}
