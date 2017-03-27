package com.github.jotask.rosjam.game.entity.enemy;

import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.RandomWalker;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Boss
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class Boss extends Enemy {

    public Boss(Body body, Sprite sprite, Room room) {
        super(body, sprite, room);
        this.setAI(new RandomWalker(this.body));
    }

}
