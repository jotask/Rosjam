package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * SoundAssets
 *
 * @author Jose Vives Iznardo
 * @since 11/04/2017
 */
public class SoundAssets implements Disposable{

    public enum SOUND{

        EXPLOSION("explosion.wav"),
        HURT("hurt.wav"),
        SHOOT("shoot.wav");

        final String file;

        SOUND(String file) {
            this.file = file;
        }
    }

    private final Assets assets;
    private final HashMap<SOUND, Sound> map;

    SoundAssets(final Assets assets) {
        this.assets = assets;
        this.map = new HashMap<SOUND, Sound>();
    }

    void prepare(){
        for(SOUND t: SOUND.values()){
            this.map.put(t, loadSound(t));
        }
    }

    private Sound loadSound(final SOUND sound){
        Sound s = Gdx.audio.newSound(Gdx.files.internal("sounds/" + sound.file));
        return s;
    }

    public Sound getSound(final SOUND key){
        return this.map.get(key);
    }

    @Override
    public void dispose() {
        final Set<Map.Entry<SOUND, Sound>> entries = map.entrySet();
        for(Map.Entry<SOUND, Sound> k: entries){
            Sound m = k.getValue();
            m.dispose();
        }
    }

}
