package com.github.jotask.rosjam.game.dungeon.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.factory.DungeonFactory;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.entity.Entity;
import com.github.jotask.rosjam.game.entity.Player;
import com.github.jotask.rosjam.game.world.WorldManager;

/**
 * LevelManager
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class LevelManager extends Entity{

    private final WorldManager worldManager;
    private final EntityManager entities;

    private Player player;

    private int level = 0;

    private Dungeon dungeon;

    public LevelManager(WorldManager worldManager) {
        this.worldManager = worldManager;
        this.entities = EntityManager.get();
        this.player = worldManager.getPlayer();

        if(player == null) {
            System.out.println("isNull");
        }

        this.nexLevel();
    }

    @Override
    public void update() {
        dungeon.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        dungeon.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        dungeon.debug(sr);
    }

    public ConfigDungeon getDungeonConfig() {
        ConfigDungeon cd = new ConfigDungeon(this.worldManager);
        return cd;
    }

    private Dungeon generateDungeon(){
        Dungeon dungeon = DungeonFactory.generateDungeon(getDungeonConfig());
        return dungeon;
    }

    public void nexLevel(){
        this.worldManager.deleteDungeon();
        this.entities.reset();
        this.dungeon = generateDungeon();
        this.player.reset(this.dungeon.initialRoom);
    }

    public Dungeon getDungeon() { return dungeon; }

}
