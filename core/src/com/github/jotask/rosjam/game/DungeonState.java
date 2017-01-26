package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.level.LevelManager;
import com.github.jotask.rosjam.game.entity.Player;
import com.github.jotask.rosjam.game.world.WorldManager;

/**
 * DungeonState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DungeonState extends GameState {

    private static DungeonState instance;
    public static DungeonState get() {
        if(instance == null)
            throw new RuntimeException("DungeonState isNull");
        return instance;
    }

    private WorldManager worldManager;

    private Player player;

    private LevelManager level;

    private final EntityManager manager;

    public DungeonState(final Game game) {
        super(game);

        if(DungeonState.instance != null)
            throw new RuntimeException("DungeonState isNot Null");
        DungeonState.instance = this;


        this.manager = EntityManager.get();
        this.worldManager = new WorldManager(game);

        this.player = EntityFactory.generatePlayer();

        this.worldManager.setPlayer(this.player);

        this.level = new LevelManager(this.worldManager);
        this.setPlayer(this.player);

    }

    private void reset(){
        // FIXME improve when the world is going to be deleted
        this.level.nexLevel();
        this.player.reset(this.level.getDungeon().initialRoom);

    }

    @Override
    public void update() {
        if(this.player.getController().resetLevel()){
            reset();
        }

        this.level.update();
        this.worldManager.update();
        this.player.update();
        this.manager.update();

    }

    @Override
    public void postUpdate() {
        this.level.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.level.render(sb);
        this.manager.render(sb);
        this.player.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        this.level.debug(sr);
        this.worldManager.debug(sr);
    }

    @Override
    public void postDebug(ShapeRenderer sr) {
        worldManager.debug(sr);
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("DungeonState.dispose()");
        this.manager.dispose();
        DungeonState.instance = null;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }
}
