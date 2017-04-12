package com.github.jotask.rosjam.engine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MusicAssets
 *
 * @author Jose Vives Iznardo
 * @since 11/04/2017
 */
public class MusicAssets implements Disposable{

    public enum MUSIC{
        M("HeroicDemise.ogg");
        final String file;

        MUSIC(String file) {
            this.file = file;
        }
    }

    private final HashMap<MUSIC, Music> map;

    public MusicAssets() {
        this.map = new HashMap<MUSIC, Music>();
    }

    public void prepare(){
        for(MUSIC t: MUSIC.values()){
            this.map.put(t, loadSound(t));
        }
    }

    private Music loadSound(final MUSIC sound){
        Music s = Gdx.audio.newMusic(Gdx.files.internal("music/" + sound.file));
        s.setLooping(true);
        return s;
    }

    public Music getSound(final MUSIC key){
        return this.map.get(key);
    }

    @Override
    public void dispose() {
        final Set<Map.Entry<MUSIC, Music>> entries = map.entrySet();
        for(Map.Entry<MUSIC, Music> k: entries){
            Music m = k.getValue();
            m.dispose();
        }
    }

}
