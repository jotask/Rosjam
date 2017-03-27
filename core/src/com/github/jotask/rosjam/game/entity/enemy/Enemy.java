package com.github.jotask.rosjam.game.entity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    protected ArtificialIntelligence intelligence;
    protected final Room room;

    protected Sprite sprite;

    public Enemy(Body body, final Sprite sprite, final Room room) {
        super(body);
        this.room = room;
        this.sprite = sprite;
    }

    protected void setAI(final ArtificialIntelligence ai){
        if(this.intelligence != null){
            throw new RuntimeException("ai is not null");
        }
        this.intelligence = ai;
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

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        this.intelligence.debug(sr);
    }

    public void despawn(){
        // TODO despawn enemies
    }

}
