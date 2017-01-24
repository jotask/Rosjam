package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.factory.DungeonFactory;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
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

    private Dungeon dungeon;

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


        this.level = new LevelManager(this.worldManager);

        this.dungeon = DungeonFactory.generateDungeon(level.getDungeon());
        this.player = EntityFactory.generatePlayer(dungeon.initialRoom);
        this.setPlayer(this.player);

    }

    private void reset(){
        // FIXME improve when the world is going to be deleted
        this.manager.reset();
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
        this.manager.update();
    }

    @Override
    public void postUpdate() {
        this.level.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.dungeon.render(sb);
        this.manager.render(sb);
        this.player.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        this.dungeon.debug(sr);
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
