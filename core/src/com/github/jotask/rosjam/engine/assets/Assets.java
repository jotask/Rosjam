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

    public Assets() {
        this.assetManager = new AssetManager();
        this.dungeonAssets = new DungeonAssets(this);

    }

    public void loadEverything(){
        assetManager.finishLoading();

        this.dungeonAssets.prepare();
    }

    public DungeonAssets getDungeonAssets() { return dungeonAssets; }

    protected final AssetManager getAssetManager(){ return this.assetManager; }

}
