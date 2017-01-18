package com.github.jotask.rosjam.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.game.entity.Entity;

/**
 * Sprite
 *
 * @author Jose Vives Iznardo
 * @since 18/01/2017
 */
public class Sprite extends Entity{

    private final TextureRegion region;
    private final Body body;

    public Sprite(TextureRegion region, final Body body) {
        this.region = region;
        this.body = body;
    }

    @Override
    public void update() {

    }

    private Vector2 getCenter(){
        Vector2 center = new Vector2(this.body.getPosition());
        center.x -= region.getRegionWidth() / 2f;
        center.y -= region.getRegionHeight() / 2f;
        return center;
    }

    @Override
    public void render(SpriteBatch sb) {
        Vector2 p = new Vector2(this.body.getPosition());
        p.x -= 1f / 2f;
        p.y -= 1f / 2f;
        sb.draw(region, p.x , p.y, 1f, 1f);
    }

    @Override
    public void debug(ShapeRenderer sr) {

    }
}
