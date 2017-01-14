package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.dungeon.Dungeon;

/**
 * DungeonState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DungeonState extends GameState {

    private Dungeon dungeon;

    public DungeonState(final Game game) {
        super(game);
        dungeon = Factory.generateDungeon();
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) | Gdx.input.justTouched()){
            dungeon = Factory.generateDungeon();
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        dungeon.debug(sr);
    }

}
