package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Enemy
 *
 * @author Jose Vives Iznardo
 * @since 23/01/2017
 */
public class Enemy extends ControlEntity {

    private final Room room;

    private Sprite sprite;

    public Enemy(Body body, Controller controller, final Sprite sprite, final Room room) {
        super(body, controller);
        this.room = room;
        this.sprite = sprite;
    }

    @Override
    public void render(SpriteBatch sb) {
        sprite.render(sb);
    }

    @Override
    public void damage(float dmg) {
        super.damage(dmg);
        if(needsToDie()){
            room.entityDied(this);
        }
    }
}
