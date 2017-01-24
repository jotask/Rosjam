package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.input.Controller;
import com.github.jotask.rosjam.util.Sprite;

/**
 * Enemy
 *
 * @author Jose Vives Iznardo
 * @since 23/01/2017
 */
public class Enemy extends ControlEntity {

    private Sprite sprite;

    public Enemy(Body body, Controller controller, final Sprite sprite) {
        super(body, controller);
        this.sprite = sprite;
    }

    @Override
    public void render(SpriteBatch sb) {
        sprite.render(sb);
    }
}
