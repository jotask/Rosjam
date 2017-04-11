package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;

import java.util.HashMap;

/**
 * EnemyAssets
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class EnemyAssets implements Disposable{

    private final String filename = "enemies.png";
    private final Class<?> type = Texture.class;

    private Texture texture;
    private final Assets assets;

    // TODO implement m owm map to choose random regions with the same key
    private HashMap<Enemies, TextureRegion> map;

    public EnemyAssets(final Assets assets) {
        this.assets = assets;
        this.assets.getAssetManager().load(filename, type);
        this.map = new HashMap<Enemies, TextureRegion>();
    }

    public void prepare(){
        texture = (Texture) this.assets.getAssetManager().get(this.filename, this.type);
        for(Enemies t: Enemies.values())
            addTile(t);
    }

    private void addTile(final Enemies tile){
        this.map.put(tile, generateRegion(tile));
    }

    private TextureRegion generateRegion(final Enemies tile){
        TextureRegion region = new TextureRegion();
        region.setTexture(this.texture);
        region.setRegion(tile.x, tile.y, tile.width, tile.height);
        return region;
    }

    public TextureRegion getRegion(final Enemies key){
        return this.map.get(key);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

}