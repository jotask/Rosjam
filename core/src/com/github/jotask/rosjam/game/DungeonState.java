package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.controller.Controller;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.entity.Player;

/**
 * DungeonState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DungeonState extends GameState {

    private WorldManager worldManager;

    private Controller controller;

    private Dungeon dungeon;
    private Player player;

    public DungeonState(final Game game, final Controller controller) {
        super(game);

        this.worldManager = new WorldManager(game);

        dungeon = Factory.generateDungeon(this.worldManager);

        this.camera.position.set(dungeon.initialRoom.getCenter(), 10f);
        this.camera.update();

    }

    @Override
    public void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) | Gdx.input.justTouched()){
            dungeon = Factory.generateDungeon(this.worldManager);
        }
        worldManager.update();
//        System.out.println("B: " + worldManager.getWorld().getBodyCount());
    }

    @Override
    public void debug(ShapeRenderer sr) {
        dungeon.debug(sr);
    }

    @Override
    public void postDebug(ShapeRenderer sr) {
        worldManager.debug(sr);
    }
}
