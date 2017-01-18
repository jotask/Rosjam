package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * PlayerAssets
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class PlayerAssets {

    private final String filename = "players.png";
    private final Class<?> type = Texture.class;

    private final int WIDTH = 8;
    private final int HEIGHT = 8;

    private Texture texture;
    private final Assets assets;

    public enum SPRITE{
        DEFAULT (0, 0);

        int x, y;

        SPRITE(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // TODO implement m owm map to choose random regions with the same key
    private HashMap<SPRITE, TextureRegion> map;

    public PlayerAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);

        this.map = new HashMap<SPRITE, TextureRegion>();

    }

    public void prepare(){
        texture = (Texture) this.assets.getAssetManager().get(this.filename, this.type);

        for(SPRITE t: SPRITE.values()){
            addTile(t);
        }

    }

    private void addTile(final SPRITE tile){
        this.map.put(tile, generateRegion(tile));
    }

    private TextureRegion generateRegion(final SPRITE tile){
        TextureRegion region = new TextureRegion();
        region.setTexture(this.texture);
        region.setRegion(tile.x * WIDTH,tile.y * HEIGHT, WIDTH, HEIGHT);
        return region;
    }

    public TextureRegion getRegion(final SPRITE key){
        return this.map.get(key);
    }

}
