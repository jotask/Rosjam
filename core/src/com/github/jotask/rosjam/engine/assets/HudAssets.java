package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * HudAssets
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
public class HudAssets {

    private final String filename = "life.png";
    private final Class<?> type = Texture.class;

    private Texture texture;
    private final Assets assets;

    public enum SPRITE{
        FULL_HEARTH (0, 0, 10, 10),
        HALF_HEARTH (10, 0, 10, 10),
        EMPTY_HEARTH (20, 0, 10, 10);

        final int x, y, w, h;

        SPRITE(int x, int y, int w, int h){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    // TODO implement m owm map to choose random regions with the same key
    private HashMap<HudAssets.SPRITE, TextureRegion> map;

    public HudAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);

        this.map = new HashMap<HudAssets.SPRITE, TextureRegion>();

    }

    public void prepare(){
        texture = (Texture) this.assets.getAssetManager().get(this.filename, this.type);

        for(HudAssets.SPRITE t: HudAssets.SPRITE.values()){
            addTile(t);
        }

    }

    private void addTile(final HudAssets.SPRITE tile){
        this.map.put(tile, generateRegion(tile));
    }

    private TextureRegion generateRegion(final HudAssets.SPRITE tile){
        TextureRegion region = new TextureRegion();
        region.setTexture(this.texture);
        region.setRegion(tile.x,tile.y, tile.w, tile.h);
        return region;
    }

    public TextureRegion getRegion(final HudAssets.SPRITE key){
        return this.map.get(key);
    }
}
