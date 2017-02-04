package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * DungeonAssets
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class DungeonAssets implements Disposable {

//    https://www.leshylabs.com/apps/sstool/

    private final Assets assets;

    private TextureAtlas atlas;
    private TextureRegion background;
    private AssetManager manager;

    public DungeonAssets(final Assets assets) {
        this.assets = assets;
        this.atlas = new TextureAtlas(Gdx.files.internal("convert/sprites.atlas"));
        this.manager = new AssetManager();
        this.manager.load("convert/bg.png", Texture.class);
        this.manager.finishLoading();
        Texture text = manager.get("convert/bg.png", Texture.class);
        this.background = new TextureRegion(text);
    }

    public TextureRegion get(Tiles tile) {
        return this.atlas.findRegion(tile.name);
    }

    @Override
    public void dispose() {
        this.atlas.dispose();
        this.manager.dispose();
    }

    public TextureRegion getBackground() { return this.background; }

}
