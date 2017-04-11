package com.github.jotask.rosjam.game.entity.enemy.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.Neat;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.game.item.Sword;
import com.github.jotask.rosjam.util.Sprite;

/**
 * GoblinMele
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class GoblinMele extends Enemy {

    public final Sword sword;

    public GoblinMele(Body body, Sprite sprite, Room room) {
        super(body, sprite, room);
        this.setAI(new Neat(this));
        this.sword = EntityFactory.getSword(this);
    }

    @Override
    public void update() {
        super.update();
        this.sword.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        this.sword.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        this.sword.debug(sr);
    }

}
