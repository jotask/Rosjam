package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Entity
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public abstract class Entity {

    protected boolean needsToDie = false;

    public abstract void update();

    public abstract void render(final SpriteBatch sb);

    public void debug(final ShapeRenderer sr){}

    public void die(){ }

    public boolean needsToDie(){ return needsToDie; }

    public void kill(){ needsToDie = true; }

}
