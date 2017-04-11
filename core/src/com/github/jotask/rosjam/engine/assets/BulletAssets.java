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

    private final String filename = "bullet.png";
    private final Class<?> type = Texture.class;

    private final int WIDTH = 16;
    private final int HEIGHT = 16;

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
    private HashMap<BulletAssets.SPRITE, TextureRegion> map;

    public BulletAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);

        this.map = new HashMap<BulletAssets.SPRITE, TextureRegion>();

    }

    public void prepare(){
        texture = (Texture) this.assets.getAssetManager().get(this.filename, this.type);

        for(BulletAssets.SPRITE t: BulletAssets.SPRITE.values()){
            addTile(t);
        }

    }

    private void addTile(final BulletAssets.SPRITE tile){
        this.map.put(tile, generateRegion(tile));
    }

    private TextureRegion generateRegion(final BulletAssets.SPRITE tile){
        TextureRegion region = new TextureRegion();
        region.setTexture(this.texture);
        region.setRegion(tile.x * WIDTH,tile.y * HEIGHT, WIDTH, HEIGHT);
        return region;
    }

    public TextureRegion getRegion(final BulletAssets.SPRITE key){
        return this.map.get(key);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

}
