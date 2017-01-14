package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.states.AbstractState;
import com.github.jotask.rosjam.game.dungeon.Dungeon;

/**
 * DungeonState
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DungeonState extends AbstractState {

    private final Game game;

    private Dungeon dungeon;

    public DungeonState(final Game game) {
        this.game = game;
        dungeon = Factory.generateDungeon();
    }

    @Override
    public void debug(ShapeRenderer sr) {
        dungeon.debug(sr);
    }

}
