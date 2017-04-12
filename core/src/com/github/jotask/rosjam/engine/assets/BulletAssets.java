package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * BulletAssets
 *
 * @author Jose Vives Iznardo
 * @since 26/01/2017
 */
public class BulletAssets implements Disposable{

    private final String filename = "weapons.png";
    private final Class<?> type = Texture.class;

    private Texture texture;
    private final Assets assets;

    public enum SPRITE{
        DEFAULT (0, 0, 16, 16),
        MAZE (16,0,6,16);

        public final int x, y, width, height;

        SPRITE(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private HashMap<SPRITE, TextureRegion> map;

    public BulletAssets(final Assets assets) {
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
        region.setRegion(tile.x, tile.y, tile.width, tile.height);
        return region;
    }

    public TextureRegion getRegion(final SPRITE key){
        return this.map.get(key);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

}
