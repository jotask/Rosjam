package com.github.jotask.rosjam.game.entity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.ArtificialIntelligence;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.HealthEntity;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Enemy
 *
 * @author Jose Vives Iznardo
 * @since 23/01/2017
 */
public abstract class Enemy extends HealthEntity {

    private ArtificialIntelligence intelligence;
    private final Room room;

    private Sprite sprite;

    public Enemy(Body body, ArtificialIntelligence intelligence, final Sprite sprite, final Room room) {
        super(body);
        this.room = room;
        this.sprite = sprite;
        this.intelligence = intelligence;
    }

    public Room getRoom() { return room; }

    @Override
    public void update() {
        this.intelligence.update();
        this.sprite.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.sprite.render(sb);
    }

    public void despawn(){
        // TODO despawn enemies
    }

}
