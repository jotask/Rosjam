package com.github.jotask.rosjam.game.entity.enemy.enemies;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.AStar;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.util.Sprite;

/**
 * GoblinMagic
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class GoblinMagic extends Enemy {

    public GoblinMagic(Body body, Sprite sprite, Room room) {
        super(body, sprite, room);
        AStar ai = new AStar(body, this.room);
        this.setAI(ai);

    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        this.intelligence.debug(sr);
    }
}
