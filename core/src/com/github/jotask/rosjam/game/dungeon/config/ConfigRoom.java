package com.github.jotask.rosjam.game.dungeon.config;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.WorldManager;

/**
 * ConfigRoom
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public final class ConfigRoom {

    public Vector2 position = new Vector2();

    public final WorldManager worldManager;

    public long seed = 23;

    public ConfigRoom(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

}
