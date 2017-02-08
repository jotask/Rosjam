package com.github.jotask.rosjam.game.entity.enemy;

import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.ArtificialIntelligence;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Boss
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class Boss extends Enemy {

    public Boss(Body body, ArtificialIntelligence intelligence, Sprite sprite, Room room) {
        super(body, intelligence, sprite, room);
    }

}
