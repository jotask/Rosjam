package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.assets.AssetManager;

/**
 * Assets
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class Assets{

    private AssetManager assetManager;

    private DungeonAssets dungeonAssets;
    private PlayerAssets playerAssets;

    public Assets() {
        this.assetManager = new AssetManager();
        this.dungeonAssets = new DungeonAssets(this);
        this.playerAssets = new PlayerAssets(this);
    }

    public void loadEverything(){
        assetManager.finishLoading();

        this.dungeonAssets.prepare();
        this.playerAssets.prepare();

    }

    public DungeonAssets getDungeonAssets() { return dungeonAssets; }

    public PlayerAssets getPlayerAssets() { return playerAssets; }

    protected final AssetManager getAssetManager(){ return this.assetManager; }

}
