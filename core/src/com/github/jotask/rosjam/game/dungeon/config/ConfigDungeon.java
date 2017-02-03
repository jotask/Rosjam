package com.github.jotask.rosjam.game.dungeon.config;

import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.DungeonAssets;
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

    public final DungeonAssets dungeonAssets;

    public final JRandom random;

    public final WorldManager worldManager;

    public int maxRooms = 2;

    public ConfigDungeon(){
        this(DungeonState.get().getWorldManager());
    }

    public ConfigDungeon(WorldManager worldManager) {
        this.worldManager = worldManager;
        this.random = new JRandom();
        this.dungeonAssets = Rosjam.get().getAssets().getDungeonAssets();
    }

}
