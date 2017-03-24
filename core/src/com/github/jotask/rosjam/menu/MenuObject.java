package com.github.jotask.rosjam.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * MenuObject
 *
 * @author Jose Vives Iznardo
 * @since 24/03/2017
 */
public interface MenuObject {

    void update();

    void render(final SpriteBatch sb);

    void debug(final ShapeRenderer sr);

}
