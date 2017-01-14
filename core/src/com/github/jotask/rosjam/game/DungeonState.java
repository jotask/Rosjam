package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    private Dungeon dungeon;
    private Player player;

    public DungeonState(final Game game) {
        super(game);

        this.worldManager = new WorldManager(game);

        dungeon = Factory.generateDungeon();

        player = Factory.generatePlayer(this.worldManager, dungeon.initialRoom);

        this.camera.position.set(dungeon.initialRoom.getCenter(), 10f);
        this.camera.update();

    }

    @Override
    public void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) | Gdx.input.justTouched()){
            dungeon = Factory.generateDungeon();
        }
        worldManager.update();
        player.update();
    }

    @Override
    public void debug(ShapeRenderer sr) {
        dungeon.debug(sr);
        worldManager.debug(sr);
    }

}
