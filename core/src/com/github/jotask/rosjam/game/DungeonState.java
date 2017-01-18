package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.factory.DungeonFactory;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.level.LevelManager;
import com.github.jotask.rosjam.game.entity.Player;

/**
 * DungeonState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DungeonState extends com.github.jotask.rosjam.engine.states.GameState {

    private WorldManager worldManager;

    private Dungeon dungeon;

    private Player player;

    private LevelManager level;

    public DungeonState(final Game game) {
        super(game);

        this.worldManager = new WorldManager(game);

        this.level = new LevelManager(this.worldManager);

        dungeon = DungeonFactory.generateDungeon(level.getDungeon());
        this.player = EntityFactory.generatePlayer(worldManager, dungeon.initialRoom);
        this.setPlayer(this.player);

    }

    private void reset(){
        // FIXME improve when the world is going to be deleted
        this.dungeon = DungeonFactory.generateDungeon(level.getDungeon());
        this.player.reset(this.dungeon.initialRoom);

    }

    @Override
    public void update() {
        if(this.player.getController().resetLevel()){
            reset();
        }
        this.worldManager.update();
        this.dungeon.update();
        this.player.update();
    }

    @Override
    public void postUpdate() {
        this.level.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        dungeon.render(sb);
        player.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        dungeon.debug(sr);
        this.worldManager.debug(sr);
    }

    @Override
    public void postDebug(ShapeRenderer sr) {
        worldManager.debug(sr);
    }

}
