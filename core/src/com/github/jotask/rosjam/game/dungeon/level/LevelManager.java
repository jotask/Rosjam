package com.github.jotask.rosjam.game.dungeon.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.WorldManager;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.entity.Entity;

/**
 * LevelManager
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class LevelManager extends Entity{

    private final WorldManager worldManager;

    private int level = 0;

    public LevelManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) { }

    @Override
    public void debug(ShapeRenderer sr) { }

    public ConfigDungeon getDungeon() {
        ConfigDungeon cd = new ConfigDungeon(this.worldManager);
        return cd;
    }
}
