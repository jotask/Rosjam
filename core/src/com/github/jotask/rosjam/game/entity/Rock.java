package com.github.jotask.rosjam.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Rock
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
public class Rock extends BodyEntity {

    private TextureRegion region;

    public Rock(Body body, final TextureRegion region) {
        super(body);
        this.region = region;
    }

    @Override
    public void render(SpriteBatch sb) {
        final Vector2 v = getBody().getPosition();
        sb.draw(region, v.x - .5f, v.y - .5f, 1f, 1f);
    }

}
