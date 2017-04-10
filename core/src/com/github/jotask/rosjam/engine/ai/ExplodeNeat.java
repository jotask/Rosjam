package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.entity.player.Player;
import com.github.jotask.rosjam.neat.jneat.network.Network;
import com.github.jotask.rosjam.neat.jneat.util.Constants;
import com.github.jotask.rosjam.util.Timer;

/**
 * ExplodeNeat
 *
 * @author Jose Vives Iznardo
 * @since 10/04/2017
 */
public class ExplodeNeat extends ArtificialIntelligence {

    private final float SPEED = 25f;

    private final Network network;

    private Timer timer;

    private final Player player;

    private final float THRESHOLD;

    public ExplodeNeat(Body body) {
        super(body);
        this.network = Game.get().neatThread.getBestNetwork();
        this.THRESHOLD = Game.get().neatThread.getThreshold();
        this.timer = new Timer(.1f);
        this.player = EntityManager.get().getPlayer();
    }

    @Override
    public void update() {
        if(timer.isPassed(true)) {
            setOutput(network.evaluate(getInputs()));
            this.dir.scl(SPEED);
            this.body.applyForceToCenter(dir, true);
            this.dir.setZero();
        }
        if(needsExplode()){
            // TODO Explode
            explode();
//            particleExplosion(body.getWorldCenter(), 100f);
        }
    }

    private boolean needsExplode(){ return (this.body.getPosition().dst2(player.getBody().getPosition()) <  2f); }

    private double[] getInputs() {
        final double[] inputs = new double[Constants.INPUTS];
        inputs[Constants.Inputs.enemy_x.ordinal()] = this.body.getPosition().x;
        inputs[Constants.Inputs.enemy_y.ordinal()] = this.body.getPosition().y;
        final Vector2 p = EntityManager.get().get().getPlayer().getBody().getPosition();
        inputs[Constants.Inputs.player_x.ordinal()] = p.x;
        inputs[Constants.Inputs.player_y.ordinal()] = p.y;
        inputs[Constants.Inputs.bias.ordinal()] = 1.0d;
        return inputs;
    }

    private void setOutput(final double[] output) {
        if((output[Constants.Outputs.left.ordinal()]) > THRESHOLD) {
            this.dir.add(-1, 0);
        }
        if(output[Constants.Outputs.right.ordinal()] > THRESHOLD) {
            this.dir.add(1, 0);
        }
        if(output[Constants.Outputs.up.ordinal()] > THRESHOLD) {
            this.dir.add(0, 1);
        }
        if(output[Constants.Outputs.down.ordinal()] > THRESHOLD) {
            this.dir.add(0, -1);
        }
    }

    public void explode(){
        explode(100, 100f, 100f, this.body.getPosition().x, this.body.getPosition().y);
    }

    private void explode(final int numRays, float blastRadius, final float blastPower, float posX, float posY) {
        final Vector2 center = new Vector2(posX, posY);
        Vector2 rayDir = new Vector2();
        Vector2 rayEnd = new Vector2();

        for(int i = 0; i < numRays; i++) {
            float angle = (i / (float) numRays) * 360 * MathUtils.degreesToRadians;
            rayDir.set(MathUtils.sin(angle), MathUtils.cos(angle));
            rayEnd.set(center.x + blastRadius * rayDir.x, center.y + blastRadius * rayDir.y);

            RayCastCallback callback = new RayCastCallback() {

                @Override
                public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                    applyBlastImpulse(fixture.getBody(), center, point, blastPower / (float)numRays);
                    return 0;
                }
            };

            this.body.getWorld().rayCast(callback, center, rayEnd);

        }

        // TODO Kill this enemy when explode

    }

    private void applyBlastImpulse(final Body body, final Vector2 blastCenter, final Vector2 applyPoint, float blastPower){
        Vector2 blastDir = applyPoint.cpy().sub(blastCenter);
        float distance = blastDir.len();
        if(distance == 0) return;
        float invDistance = 1f / distance;
        float impulseMag = Math.min(blastPower * invDistance, blastPower * 0.5f); //Not physically correct

        body.applyForce(blastDir.nor().scl(impulseMag), applyPoint, true);

        if(body.getUserData() instanceof Player){
            final Player p = (Player) body.getUserData();
            // TODO apply damage
//            p.damage(3);
        }
    }

    private void particleExplosion(final Vector2 center, final float blastPower){
        final World world = body.getWorld();
        int numRays = 100;
        for (int i = 0; i < numRays; i++) {
            float angle = (i / (float) numRays) * 360 * MathUtils.degreesToRadians;
            Vector2 rayDir = new Vector2( MathUtils.sin(angle), MathUtils.cos(angle) );

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.DynamicBody;
            bd.fixedRotation = true; // rotation not necessary
            bd.bullet = true; // prevent tunneling at high speed
            bd.linearDamping = 10; // drag due to moving through air
            bd.gravityScale = 0; // ignore gravity
            bd.position.set(center); // start at blast center
            bd.linearVelocity.set(rayDir.scl(blastPower));

            Body body = world.createBody(bd);

            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(0.05f); // very small

            FixtureDef fd = new FixtureDef();
            fd.shape = circleShape;
            fd.density = 60 / (float)numRays; // very high - shared across all particles
            fd.friction = 0; // friction not necessary
            fd.restitution = 0.99f; // high restitution to reflect off obstacles
            fd.filter.groupIndex = -1; // particles should not collide with each other
            body.createFixture(fd);

        }
    }

}
