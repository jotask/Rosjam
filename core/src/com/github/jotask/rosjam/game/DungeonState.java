package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.level.LevelManager;
import com.github.jotask.rosjam.game.effects.Effects;
import com.github.jotask.rosjam.game.entity.player.Player;
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

    private final InputProcessor inputProcessor;

    public final Effects effects;

    DungeonState(final Game game) {
        super(game);
        this.manager = EntityManager.get();
        this.inputProcessor = Gdx.input.getInputProcessor();
        this.effects = new Effects();
    }

    void init(){

        final InitialParameters.Cfg cfg = InitialParameters.load();

        this.worldManager = new WorldManager(game);

        final Player player = EntityFactory.generatePlayer(this.worldManager);
        player.setCurrentHealth(cfg.health);

        this.manager.createPlayer(player);

        this.level = new LevelManager(this.worldManager);

        this.score = new Score(cfg);

        this.level.loadLevel(cfg);

        this.hud = new DungeonHud(this);

    }

    @Override
    public void update() {

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
        this.effects.render(sb);
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
        this.worldManager.dispose();
        this.effects.dispose();
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public LevelManager getLevel() { return level; }

    public DungeonHud getHud() { return hud; }

    @Override
    public void enterState() { Gdx.input.setInputProcessor(this.inputProcessor); }

    @Override
    public void resize(int width, int height) {
        this.hud.resize(width, height);
    }

}
