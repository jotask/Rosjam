package com.github.jotask.rosjam.game.dungeon.config;

import com.badlogic.gdx.math.MathUtils;
import com.github.jotask.rosjam.game.InitialParameters;
import com.github.jotask.rosjam.util.JRandom;

/**
 * ConfigDungeon
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class ConfigDungeon {

    public final long seed;

    public final JRandom random;

    public final int level;

    public final int maxRooms;

    public ConfigDungeon() {
        this.seed = MathUtils.random(Long.MAX_VALUE);
        this.random = new JRandom(this.seed);
        this.level = 1;
        this.maxRooms = 7;
    }

    public ConfigDungeon(final ConfigDungeon cfg){
        this.seed = cfg.seed;
        this.random = cfg.random;
        this.level = cfg.level + 1;
        this.maxRooms = cfg.maxRooms + 1;

    }

    public ConfigDungeon(InitialParameters.Cfg cfg) {
        this.seed = cfg.seed;
        this.random = new JRandom(this.seed);
        this.level = cfg.level;
        this.maxRooms = cfg.maxRooms;
    }
}
