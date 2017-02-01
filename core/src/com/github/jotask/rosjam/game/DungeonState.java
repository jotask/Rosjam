package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.game.dungeon.level.LevelManager;
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

    private LevelManager level;

    private final EntityManager manager;

    public DungeonState(final Game game) {
        super(game);

        if(DungeonState.instance != null)
            throw new RuntimeException("DungeonState isNot Null");
        DungeonState.instance = this;

        this.worldManager = new WorldManager(game);
        this.manager = EntityManager.get();

        this.level = new LevelManager(this.worldManager);
        this.level.nextLevel();

    }

    private void reset(){
        // FIXME improve when the world is going to be deleted
        this.level.nextLevel();
    }

    @Override
    public void update() {
        if(manager.getPlayer().getController().resetLevel()){
            reset();
        }

        this.level.update();
        this.worldManager.update();
        this.manager.update();

        Camera.follow(EntityManager.get().getPlayer());

    }

    @Override
    public void postUpdate() {
        this.level.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.level.render(sb);
        this.manager.render(sb);
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
        this.manager.dispose();
        DungeonState.instance = null;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public LevelManager getLevel() { return level; }
}
