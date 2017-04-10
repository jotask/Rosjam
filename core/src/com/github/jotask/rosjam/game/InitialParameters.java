package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.github.jotask.rosjam.game.entity.HealthEntity;
import com.github.jotask.rosjam.game.entity.player.Player;

/**
 * InitialParameters
 *
 * @author Jose Vives Iznardo
 * @since 07/04/2017
 */
public final class InitialParameters {

    public static final String file = "state.json";

    private InitialParameters(){}

    static void save(final DungeonState game){
        Json json = new Json();
        FileHandle fileHandle = Gdx.files.local(file);
        fileHandle.writeString(json.prettyPrint(new Cfg(game)), false);
    }

    static Cfg load(){
        Json json = new Json();
        FileHandle fileHandle = Gdx.files.local(file);
        Cfg cfg;
        if(fileHandle.exists()){
            cfg = json.fromJson(Cfg.class, fileHandle.readString());
        }else{
            cfg = new Cfg();
        }
        return cfg;
    }

    public static class Cfg implements Json.Serializable{

        // TODO set default seed
        public long seed;
        public int level;
        public long score;

        // Player
        public int health;

        Cfg(final DungeonState game) {

            this.score = game.score.getScore();
            this.level = game.getLevel().getLevel();

            final Player player = EntityManager.get().getPlayer();
            this.health = player.currentHealth;

        }

        Cfg(){

            this.seed = MathUtils.random(Long.MAX_VALUE);
            this.level = 0;
            this.score = 627;

            this.health = HealthEntity.MAX_HEALTH;

        }

        @Override
        public void write(Json json) {
            json.writeValue("seed", this.seed);
            json.writeValue("level", this.level);
            json.writeValue("score", this.score);

            // Players
            json.writeValue("health", this.health);

        }

        @Override
        public void read(Json json, JsonValue jsonData) {
            this.seed  = jsonData.getLong("seed", MathUtils.random(Long.MAX_VALUE));
            this.level = jsonData.getInt("level");
            this.score = jsonData.getLong("score");

            // Player
            this.health = jsonData.getInt("health");
        }

    }

}
