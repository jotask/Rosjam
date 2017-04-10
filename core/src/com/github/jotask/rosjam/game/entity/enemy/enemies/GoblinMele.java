package com.github.jotask.rosjam.game.entity.enemy.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.ExplodeNeat;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.util.Sprite;

/**
 * GoblinMele
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class GoblinMele extends Enemy {

    public GoblinMele(Body body, Sprite sprite, Room room) {
        super(body, sprite, room);
        this.setAI(new ExplodeNeat(this.body));
    }

}
