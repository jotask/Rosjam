package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * HealthEntity
 *
 * @author Jose Vives Iznardo
 * @since 24/01/2017
 */
public abstract class HealthEntity extends BodyEntity {

    private final int MAX_HEALTH;
    public int currentHealth;

    protected HealthEntity(final Body body) {
        this(body, 10);
    }

    protected HealthEntity(final Body body, final int MAXHEALTH) {
        super(body);
        this.MAX_HEALTH = MAXHEALTH;
    }

    @Override
    public void update() {
        super.update();
    }

    public void damage(int dmg){
        this.currentHealth  -= dmg;
        if(this.currentHealth <= 1){
            this.needsToDie = true;
        }else if(this.currentHealth > MAX_HEALTH){
            this.currentHealth = MAX_HEALTH;
        }
    }

    public int getMAX_HEALTH() { return MAX_HEALTH; }

    public int getCurrentHealth() { return currentHealth; }

    public void setCurrentHealth(int health){
        this.currentHealth = health;
    }

}
