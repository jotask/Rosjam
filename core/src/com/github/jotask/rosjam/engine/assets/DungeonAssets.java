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

    private final Assets assets;

    public enum TILES{
        FLOOR ()
    }

    // TODO implement m owm map to choose random regions with the same key
    private HashMap<TILES, TextureRegion> map;

    public DungeonAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);

        this.map = new HashMap<TILES, TextureRegion>();

    }

    public void prepare(){
        Texture texture = (Texture) this.assets.getAssetManager().get(this.filename, this.type);

        TextureRegion region = new TextureRegion();
        region.setTexture(texture);
        region.setRegion(0,0,SIZE,SIZE);
        this.map.put(TILES.FLOOR, region);

    }

    public TextureRegion getRegion(final TILES key){
        return this.map.get(key);
    }

}
