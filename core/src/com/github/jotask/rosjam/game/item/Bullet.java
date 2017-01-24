package com.github.jotask.rosjam.game.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.game.entity.BodyEntity;
import com.github.jotask.rosjam.util.Sprite;
import com.github.jotask.rosjam.util.Timer;

/**
 * Bullet
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public class Bullet extends BodyEntity{

    private final float damage = 150f;

    private final float SPEED = 10f;

    private Sprite sprite;

    private final Timer timer;

    public Bullet(Body body, final Sprite sprite) {
        super(body);
        this.sprite = sprite;
        timer = new Timer(1f);
    }

    public void shot(Vector2 direction){
        // TODO ADD DIRECTION
        Vector2 d = new Vector2();

        if(direction.x < -.5f){
            d.x = -1;
        }else if(direction.x > .5f){
            d.x = 1;
        } else{
            d.x = 0;
        }

        if(direction.y < -.5f){
            d.y = -1;
        }else if(direction.y > .5f){
            d.y = 1;
        } else{
            d.y = 0;
        }

        this.getBody().setLinearVelocity(direction.scl(SPEED));

    }

    @Override
    public void render(SpriteBatch sb) {
        sprite.render(sb);
    }

    @Override
    public boolean needsToDie() {
        return timer.isPassed() || !body.isAwake();
    }

    public float getDamage() { return damage; }
}
