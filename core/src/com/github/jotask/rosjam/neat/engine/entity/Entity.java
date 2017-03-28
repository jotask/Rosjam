package com.github.jotask.rosjam.neat.engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Entity
 *
 * @author Jose Vives Iznardo
 * @since 11/02/2017
 */
public abstract class Entity {

    public enum Type{ ENEMY, PLAYER, FOOD }

    private final Body body;

    protected boolean die;

    public Entity(Body body) {
        this.body = body;
        this.body.setUserData(this);
    }

    public void input(){

    }

    public void update(){

    }

    public void render(SpriteBatch sb){

    }

    public void debug(ShapeRenderer sr){

    }

    protected void clearForces(){
        this.getBody().setLinearVelocity(0,0);
        this.getBody().setAngularVelocity(0);
    }

    public void die(){ }

    public boolean isDie() { return die; }

    public Body getBody() { return body; }

    public void kill(){ this.die = true; }

}
