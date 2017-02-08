package com.github.jotask.rosjam.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Sprite
 *
 * @author Jose Vives Iznardo
 * @since 18/01/2017
 */
public class Sprite{

    private final Animation<TextureRegion> animation;

    private TextureRegion region;

    private float state;

    private Body body;

    public Sprite(final Body body, Animation<TextureRegion> animation) {
        this.body = body;
        this.animation = animation;
        this.state = 0;
    }

    public void update() {
        state += Gdx.graphics.getDeltaTime();
        region = animation.getKeyFrame(state, true);
    }

    public void render(SpriteBatch sb) {
        update();
        Vector2 pos = this.body.getPosition();
        Vector2 p = new Vector2(pos).sub(.5f, .5f);

        float dir = body.getLinearVelocity().x;
        if(dir < 0){
            if(!region.isFlipX())
            region.flip(true, false);
        }else{
            if(region.isFlipX())
                region.flip(true, false);
        }

        sb.draw(region, p.x , p.y, 1f, 1f);
    }

    public void debug(ShapeRenderer sr) { }

}
