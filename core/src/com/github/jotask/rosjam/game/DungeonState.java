package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.level.LevelManager;
import com.github.jotask.rosjam.game.hud.dungeon.DungeonHud;
import com.github.jotask.rosjam.game.world.WorldManager;

/**
 * DungeonState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DungeonState extends GameState {

    private DungeonHud hud;

    public Score score;

    private WorldManager worldManager;

    private LevelManager level;

    private final EntityManager manager;

    public DungeonState(final Game game) {
        super(game);
        this.manager = EntityManager.get();
    }

    void init(){

        this.worldManager = new WorldManager(game);

        this.manager.createPlayer(EntityFactory.generatePlayer(this.worldManager));

        this.level = new LevelManager(this.worldManager);

        score = new Score();

        this.level.nextLevel();

        this.hud = new DungeonHud(this);
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

        this.score.update();
        this.hud.update();

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
    public void postRender(SpriteBatch sb) {
        this.hud.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        this.level.debug(sr);
        this.worldManager.debug(sr);
    }

    @Override
    public void postDebug(ShapeRenderer sr) {
        this.worldManager.debug(sr);
        this.hud.debug(sr);
    }

    @Override
    public void dispose() {
        this.manager.dispose();
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public LevelManager getLevel() { return level; }

}
