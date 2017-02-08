package com.github.jotask.rosjam.game.entity.enemy.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.RandomWalker;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Snake
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class Snake extends Enemy {

    public Snake(Body body, Sprite sprite, Room room) {
        super(body, new RandomWalker(body), sprite, room);
    }

}
