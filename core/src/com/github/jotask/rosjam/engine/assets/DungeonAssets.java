package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * DungeonAssets
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class DungeonAssets {

    private final String filename = "dungeon_sheet.png";
    private final Class<?> type = Texture.class;

    private final int SIZE = 16;

    private Texture texture;
    private final Assets assets;

    public enum TILES{
        FLOOR (6, 2),
        WALL_LEFT(5, 2),
        WALL_RIGHT(7, 2),
        WALL_TOP(6, 1),
        WALL_BOTTOM(6, 3);

        int x, y;

        TILES(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // TODO implement m owm map to choose random regions with the same key
    private HashMap<TILES, TextureRegion> map;

    public DungeonAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);

        this.map = new HashMap<TILES, TextureRegion>();

    }

    public void prepare(){
        texture = (Texture) this.assets.getAssetManager().get(this.filename, this.type);

        for(TILES t: TILES.values()){
            addTile(t);
        }

    }

    private void addTile(final TILES tile){
        this.map.put(tile, generateRegion(tile));
    }

    private TextureRegion generateRegion(final TILES tile){
        TextureRegion region = new TextureRegion();
        region.setTexture(this.texture);
        region.setRegion(tile.x * SIZE,tile.y * SIZE,SIZE,SIZE);
        return region;
    }

    public TextureRegion getRegion(final TILES key){
        return this.map.get(key);
    }

}
