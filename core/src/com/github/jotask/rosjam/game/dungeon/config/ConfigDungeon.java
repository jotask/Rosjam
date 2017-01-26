package com.github.jotask.rosjam.game.dungeon.config;

import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.world.WorldManager;

/**
 * ConfigDungeon
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class ConfigDungeon {

    public int maxRooms = 7;

    public long seed = 23;

    public final WorldManager worldManager;

    public ConfigDungeon(){
        this(DungeonState.get().getWorldManager());
    }

    public ConfigDungeon(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

}
