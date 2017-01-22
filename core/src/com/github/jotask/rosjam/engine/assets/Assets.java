package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Assets
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class Assets{

    private AssetManager assetManager;

    private BitmapFont font;

    private DungeonAssets dungeonAssets;
    private PlayerAssets playerAssets;

    public Assets() {
        this.assetManager = new AssetManager();
        this.dungeonAssets = new DungeonAssets(this);
        this.playerAssets = new PlayerAssets(this);
    }

    public void loadEverything(){
        assetManager.finishLoading();

        this.font = new BitmapFont();

        this.dungeonAssets.prepare();
        this.playerAssets.prepare();

    }

    public BitmapFont getFont() { return font; }

    public DungeonAssets getDungeonAssets() { return dungeonAssets; }

    public PlayerAssets getPlayerAssets() { return playerAssets; }

    protected final AssetManager getAssetManager(){ return this.assetManager; }

}
