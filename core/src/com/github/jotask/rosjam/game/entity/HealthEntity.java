package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * HealthEntity
 *
 * @author Jose Vives Iznardo
 * @since 24/01/2017
 */
public abstract class HealthEntity extends BodyEntity {

    public static final int MAX_HEALTH = 10;
    public int currentHealth;

    protected HealthEntity(Body body) {
        super(body);

        // FIXME
        this.currentHealth = MAX_HEALTH / 2;

    }

    @Override
    public void update() {
        super.update();
    }

    public void damage(int dmg){
        this.currentHealth  -= dmg;
        if(this.currentHealth <= 1){
            this.needsToDie = true;
        }
    }

    public int getMAX_HEALTH() { return MAX_HEALTH; }

    public int getCurrentHealth() { return currentHealth; }

    public void setCurrentHealth(int health){
        this.currentHealth = health;
    }

}
