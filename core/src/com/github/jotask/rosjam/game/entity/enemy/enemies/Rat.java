package com.github.jotask.rosjam.game.entity.enemy.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.RandomWalker;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Rat
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class Rat extends Enemy {

    public Rat(Body body, Sprite sprite, Room room) {
        super(body, sprite, room);
        this.setAI(new RandomWalker(this.body));
    }

}
