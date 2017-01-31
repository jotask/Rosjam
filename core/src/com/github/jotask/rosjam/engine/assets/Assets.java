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
    private BulletAssets bulletAssets;

    public Assets() {
        this.assetManager = new AssetManager();
        this.dungeonAssets = new DungeonAssets(this);
        this.playerAssets = new PlayerAssets(this);
        this.bulletAssets = new BulletAssets(this);
    }

    public void loadEverything(){
        assetManager.finishLoading();

        this.font = new BitmapFont();

        this.playerAssets.prepare();
        this.bulletAssets.prepare();

    }

    public BitmapFont getFont() { return font; }

    public DungeonAssets getDungeonAssets() { return dungeonAssets; }

    public PlayerAssets getPlayerAssets() { return playerAssets; }

    public BulletAssets getBulletAssets() { return bulletAssets; }

    protected final AssetManager getAssetManager(){ return this.assetManager; }

}
