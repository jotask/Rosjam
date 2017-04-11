package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * EnemyAssets
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class StateAssets implements Disposable{

    public enum Images {

        AIKO(0, 0, 100, 60),
        ROSJAM(0, 60, 142, 36);

        public final int x, y, width, height;

        Images(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private final String filename = "aiko.png";
    private final Class<?> type = Texture.class;

    private Texture texture;
    private final Assets assets;

    // TODO implement m owm map to choose random regions with the same key
    private HashMap<Images, TextureRegion> map;

    public StateAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);
        this.map = new HashMap<Images, TextureRegion>();
    }

    public void prepare(){
        texture = (Texture) this.assets.getAssetManager().get(this.filename, this.type);
        for(Images t: Images.values())
            addTile(t);
    }

    private void addTile(final Images tile){
        this.map.put(tile, generateRegion(tile));
    }

    private TextureRegion generateRegion(final Images tile){
        TextureRegion region = new TextureRegion();
        region.setTexture(this.texture);
        region.setRegion(tile.x, tile.y, tile.width, tile.height);
        return region;
    }

    public TextureRegion getRegion(final Images key){
        return this.map.get(key);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

}