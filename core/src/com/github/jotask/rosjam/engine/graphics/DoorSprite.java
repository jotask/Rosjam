package com.github.jotask.rosjam.engine.graphics;

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

    public final Animation<TextureRegion> animation;

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
        region = animation.getKeyFrame(state, false);
    }

    public void render(SpriteBatch sb, Vector2 position) {
        update();
        Vector2 p = new Vector2(position);
        sb.draw(region, p.x, p.y, 1f, 1f);
    }

    public void open(){
        this.animation.setPlayMode(Animation.PlayMode.REVERSED);
    }

    public void close(){
        this.animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

}