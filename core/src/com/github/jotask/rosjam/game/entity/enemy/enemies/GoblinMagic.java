package com.github.jotask.rosjam.game.entity.enemy.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.ExplodeNeat;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.engine.graphics.Sprite;

/**
 * GoblinMagic
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class GoblinMagic extends Enemy {

    public GoblinMagic(Body body, Sprite sprite, Room room) {
        super(body, sprite, room);
        this.setAI(new ExplodeNeat(this));
    }

}
