package com.github.jotask.rosjam.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.game.dungeon.room.TestRoom;
import com.github.jotask.rosjam.game.entity.Entity;

import java.util.LinkedList;

/**
 * Dungeon
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class DungeonTest extends Entity {

    private LinkedList<TestRoom> rooms;

    public TestRoom initialRoom;

    public DungeonTest(LinkedList<TestRoom> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void update() {
        for(TestRoom r: rooms){
            r.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for(TestRoom r: rooms){
            r.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        for(TestRoom r: rooms){
            r.debug(sr);
        }
    }
}
