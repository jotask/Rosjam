package com.github.jotask.rosjam.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * DoorSprite
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
public class DoorSprite {

    private final Animation<TextureRegion> animation;

    private TextureRegion region;

    private float state;

    private Vector2 position;

    public DoorSprite(Animation<TextureRegion> animation) {
        this.animation = animation;
        this.state = 0;
    }

    public void setPosition(Vector2 pos){
        if(this.position != null)
            throw new RuntimeException("Pos is not null");
        this.position = pos;
    }

    public void update() {
        state += Gdx.graphics.getDeltaTime();
        region = animation.getKeyFrame(state, true);
    }

    public void render(SpriteBatch sb, Vector2 position) {
        update();
        Vector2 p = new Vector2(position);
        sb.draw(region, p.x, p.y, 1f, 1f);
    }

}