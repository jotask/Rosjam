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

    public abstract void update();

    public abstract void render(final SpriteBatch sb);

    public abstract void debug(final ShapeRenderer sr);

}
