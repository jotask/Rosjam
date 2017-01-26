package com.github.jotask.rosjam.game.dungeon.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.dungeon.door.Door;
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
    private final EntityManager entityManager;
    private final DungeonManager dungeonManager;
    private final Player player;

    private int level = 0;

    private Door nextRoom;

    public LevelManager(WorldManager worldManager) {
        this.worldManager = worldManager;
        this.entityManager = EntityManager.get();
        this.dungeonManager = new DungeonManager();
        this.player = DungeonState.get().getPlayer();
    }

    @Override
    public void update() {
        if(nextRoom != null){
            nextRoom();
        }
    }

    @Override
    public void render(SpriteBatch sb) { dungeonManager.getDungeon().render(sb); }

    @Override
    public void debug(ShapeRenderer sr) { }

    public void nextLevel(){
        // Augment level
        level++;

        // Delete everything
        entityManager.reset();
        worldManager.deleteDungeon();

        // Create Everything
        dungeonManager.nextLevel();
        player.goTo(dungeonManager.getDungeon().initialRoom);

    }

    public void nextRoom(final Door door){
        // TODO Check if is the door for next level
        if(door.isOpen())
            nextRoom = door;
    }

    private void nextRoom(){
        // TODO Check if is the door for next level
        dungeonManager.enterRoom(nextRoom);
        nextRoom = null;
    }

}
