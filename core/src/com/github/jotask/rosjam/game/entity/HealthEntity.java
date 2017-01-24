package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * HealthEntity
 *
 * @author Jose Vives Iznardo
 * @since 24/01/2017
 */
public class HealthEntity extends BodyEntity {

    private final float MAX_HEALTH = 100f;
    private float currentHealth;

    protected HealthEntity(Body body) {
        super(body);

        this.currentHealth = MAX_HEALTH;

    }

    @Override
    public void update() {
        super.update();
    }

    public void damage(float dmg){
        this.currentHealth  -= dmg;
        if(this.currentHealth <= 0){
            this.needsToDie = true;
        }
    }

}
