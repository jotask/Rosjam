package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.input.Controller;

/**
 * ArtificialIntelligence
 *
 * @author Jose Vives Iznardo
 * @since 23/01/2017
 */
public class ArtificialIntelligence implements Controller {

    private AStar algorithm;

    private Vector2 dir;

    public ArtificialIntelligence() {
        this.algorithm = new AStar();
        this.dir = new Vector2();
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

}
