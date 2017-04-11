package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.rosjam.util.Converter;

/**
 * Assets
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class Assets implements Disposable{

    private AssetManager assetManager;

    private Skin skin;

    private BitmapFont font;

    private DungeonAssets dungeonAssets;
    private PlayerAssets playerAssets;
    private EnemyAssets enemyAssets;
    private BulletAssets bulletAssets;
    private StateAssets stateAssets;
    private HudAssets hudAssets;

    private SoundAssets soundAssets;
    private MusicAssets musicAssets;

    public Assets() {

        new Converter();

        this.assetManager = new AssetManager();
        this.dungeonAssets = new DungeonAssets(this);
        this.playerAssets = new PlayerAssets(this);
        this.enemyAssets = new EnemyAssets(this);
        this.bulletAssets = new BulletAssets(this);
        this.stateAssets = new StateAssets(this);
        this.hudAssets = new HudAssets(this);

        this.soundAssets = new SoundAssets(this);
        this.musicAssets = new MusicAssets(this);

    }

    public void loadEverything(){
        this.assetManager.finishLoading();

        this.font = new BitmapFont();
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        this.playerAssets.prepare();
        this.enemyAssets.prepare();
        this.bulletAssets.prepare();
        this.stateAssets.prepare();
        this.hudAssets.prepare();

        this.soundAssets.prepare();
        this.musicAssets.prepare();

    }

    public BitmapFont getFont() { return font; }

    public DungeonAssets getDungeonAssets() { return dungeonAssets; }

    public PlayerAssets getPlayerAssets() { return playerAssets; }

    public BulletAssets getBulletAssets() { return bulletAssets; }

    public EnemyAssets getEnemyAssets() { return enemyAssets; }

    public StateAssets getStateAssets() { return stateAssets; }

    final AssetManager getAssetManager(){ return this.assetManager; }

    public final Skin getSkin(){ return this.skin; }

    public HudAssets getHudAssets() { return hudAssets; }

    public SoundAssets getSoundAssets() { return soundAssets; }

    public MusicAssets getMusicAssets() { return musicAssets; }

    @Override
    public void dispose() {
        this.font.dispose();
        this.dungeonAssets.dispose();
        this.playerAssets.dispose();
        this.bulletAssets.dispose();
        this.enemyAssets.dispose();
        this.stateAssets.dispose();
        this.assetManager.dispose();
        this.skin.dispose();
        this.hudAssets.dispose();

        this.soundAssets.dispose();
        this.musicAssets.dispose();
    }
}
