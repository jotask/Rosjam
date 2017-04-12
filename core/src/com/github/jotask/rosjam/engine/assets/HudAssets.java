package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * HudAssets
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
public class HudAssets implements Disposable{

    public final Texture touchBackground;
    public final Texture touchKnob;

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

    private HashMap<SPRITE, TextureRegion> map;

    public HudAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);

        this.touchBackground = new Texture(Gdx.files.internal("touchBackground.png"));
        this.touchKnob = new Texture(Gdx.files.internal("touchKnob.png"));

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
        region.setRegion(tile.x,tile.y, tile.w, tile.h);
        return region;
    }

    public TextureRegion getRegion(final SPRITE key){
        return this.map.get(key);
    }

    @Override
    public void dispose() {
        this.touchBackground.dispose();
        this.touchKnob.dispose();
        this.texture.dispose();
    }

}
