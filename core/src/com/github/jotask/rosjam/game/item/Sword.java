package com.github.jotask.rosjam.game.item;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.BulletAssets;
import com.github.jotask.rosjam.game.entity.BodyEntity;

/**
 * Sword
 *
 * @author Jose Vives Iznardo
 * @since 11/04/2017
 */
public class Sword extends Item{

    public final int DMG = 3;

    private enum DIR{ LEFT, RIGHT, UP, DOWN }

    private boolean isAttacking;

    private BodyEntity owner;

    private static final Vector2 SIZE = com.github.jotask.rosjam.neat.engine.weapon.Sword.SIZE;

    public final Vector2 shotDirection;

    private final Body body;

    private final TextureRegion region;

    private final Color color;

    public Sword(final Body body, final BodyEntity owner) {
        this.body = body;
        this.owner = owner;
        this.color = Color.CYAN;
        this.color.a = .5f;
        this.shotDirection = new Vector2();
        this.region = Rosjam.get().getAssets().getBulletAssets().getRegion(BulletAssets.SPRITE.MAZE);
    }

    public void update() {
        final Body other = owner.getBody();
        this.body.setTransform(other.getPosition(), this.body.getAngle());
        this.isAttacking = attack();
        this.body.setAwake(this.isAttacking);
        if(this.isAttacking) {
            this.enableSword();
        }else{
            this.disableSword();
        }
        this.shotDirection.setZero();
    }

    private void enableSword(){
        final Shape s = this.body.getFixtureList().first().getShape();
        if(s instanceof PolygonShape){
            ((PolygonShape) s).setAsBox(SIZE.x, SIZE.y);
        }
    }

    private void disableSword(){
        final Shape s = this.body.getFixtureList().first().getShape();
        if(s instanceof PolygonShape){
            ((PolygonShape) s).setAsBox(0f, 0f);
        }
    }

    private boolean attack() {
        if(this.shotDirection.isZero()){
            return false;
        }
        if(this.shotDirection.x < 0){
            this.stab(180);
        }else if(this.shotDirection.x > 0){
            this.stab(-180);
        }else if(this.shotDirection.y < 0){
            this.stab(90);
        }else if(this.shotDirection.y > 0){
            this.stab(-90);
        }
        return true;
    }

    private void stab(final float angle){
        final Vector2 p = body.getPosition().cpy();
        if(angle == 180){
            p.x -= SIZE.x;
        }else if(angle == -180){
            p.x += SIZE.x;
        }else if(angle == 90){
            p.y += SIZE.x;
        }else if(angle == -90){
            p.y -= SIZE.x;
        }
        this.body.setTransform( p, (float) Math.toRadians(angle));
    }

    private DIR getDir(final int angle){
        if(angle == 90){
            return DIR.UP;
        }else if(angle == -90){
            return DIR.DOWN;
        }else if(angle == - 180){
            return DIR.RIGHT;
        }else{
            return DIR.LEFT;
        }
    }

    @Override
    public void render(SpriteBatch sb) {

        if(!this.isAttacking)
            return;

        final Double angle = Double.valueOf(Math.toDegrees(this.body.getAngle()));

        final int a = angle.intValue();

        final Vector2 p = this.body.getPosition();
        float w = SIZE.x * 2f;
        float h = SIZE.y * 2f;
        float x = p.x;
        float y = p.y;
        float aaa = 0;

        switch (getDir(a)){
            case LEFT:
                w = SIZE.y * 2f;
                h = SIZE.x * 2f;
                x = p.x - w * 2f;
                y = p.y + (h * .5f) * .25f;
                aaa = -90;
                break;
            case RIGHT:
                w = SIZE.y * 2f;
                h = SIZE.x * 2f;
                x = p.x + w * 2f;
                y = p.y - (h * .5f) * .25f;
                aaa = 90;
                break;
            case DOWN:
                w = SIZE.y * 2f;
                h = SIZE.x * 2f;
                x = p.x - w * .5f;
                y = p.y - h * .5f;
                if(region.isFlipY())
                    region.flip(false, false);
                break;
            case UP:
                w = SIZE.y * 2f;
                h = SIZE.x * 2f;
                x = p.x - w * .5f;
                y = p.y - h * .5f;
                if(!region.isFlipY())
                    region.flip(false, true);
                break;
        }

        sb.draw(region, x, y, 0, 0, w, h, 1, 1, aaa);

    }

    @Override
    public void destroyItem() {
        final World world = this.body.getWorld();
        world.destroyBody(this.body);
    }

}
