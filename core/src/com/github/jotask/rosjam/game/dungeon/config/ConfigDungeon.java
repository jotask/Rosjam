package com.github.jotask.rosjam.game.dungeon.config;

import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.world.WorldManager;
import com.github.jotask.rosjam.util.JRandom;

/**
 * ConfigDungeon
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class ConfigDungeon {

    public int maxRooms = 1;

    public final JRandom random;

    public final WorldManager worldManager;

    public ConfigDungeon(){
        this(DungeonState.get().getWorldManager());
    }

    public ConfigDungeon(WorldManager worldManager) {
        this.worldManager = worldManager;
        this.random = new JRandom();
    }

}
