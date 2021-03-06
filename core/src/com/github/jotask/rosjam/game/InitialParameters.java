package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.github.jotask.rosjam.game.entity.player.Player;

/**
 * InitialParameters
 *
 * @author Jose Vives Iznardo
 * @since 07/04/2017
 */
public final class InitialParameters {

    public static final String file = "savedata/state.json";

    public static final int PLAYER_HEALTH_INITIAL = 20;

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

        public long seed;
        public int level;
        public int maxRooms;

        public long score;

        // Player
        public int health;

        Cfg(final DungeonState game) {

            this.seed = game.getLevel().dungeonManager.getDungeon().cfg.seed;
            this.level = game.getLevel().getLevel();
            this.maxRooms = game.getLevel().dungeonManager.getDungeon().cfg.maxRooms;

            this.score = game.score.getScore();

            final Player player = EntityManager.get().getPlayer();
            this.health = player.currentHealth;

        }

        Cfg(){

            this.seed = MathUtils.random(Long.MAX_VALUE);
            this.level = 0;
            this.maxRooms = 7;

            this.score = 627;

            this.health = PLAYER_HEALTH_INITIAL;

        }

        @Override
        public void write(Json json) {
            json.writeValue("seed", this.seed);
            json.writeValue("level", this.level);
            json.writeValue("maxRooms", this.maxRooms);

            json.writeValue("score", this.score);

            // Players
            json.writeValue("health", this.health);

        }

        @Override
        public void read(Json json, JsonValue jsonData) {
            this.seed  = jsonData.getLong("seed", MathUtils.random(Long.MAX_VALUE));
            this.level = jsonData.getInt("level");
            this.maxRooms = jsonData.getInt("maxRooms");

            this.score = jsonData.getLong("score");

            // Player
            this.health = jsonData.getInt("health");
        }

    }

}
