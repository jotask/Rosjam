package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.game.entity.BodyEntity;

/**
 * ArtificialIntelligence
 *
 * @author Jose Vives Iznardo
 * @since 23/01/2017
 */
public class ArtificialIntelligence implements Controller {

    private BodyEntity entity;
    private BodyEntity target;

    private BasicAI algorithm;

    private Vector2 dir;

    public ArtificialIntelligence() {
        this.algorithm = new BasicAI(this);
        this.dir = new Vector2(0, 0);
    }

    @Override
    public Vector2 getDirection() {
        return dir;
    }

    @Override
    public boolean isShooting() {
        return false;
    }

    @Override
    public Vector2 getShootDirection() {
        return null;
    }

    @Override
    public boolean resetLevel() {
        return false;
    }

    public void setEntity(BodyEntity e) {
        if(this.entity != null){
            throw new RuntimeException("entity is not null");
        }
        this.entity = e;
    }

    public void setTarget(BodyEntity t) {
        if(this.target != null) {
            throw new RuntimeException("target is not null");
        }
        this.target = t;
    }
}
