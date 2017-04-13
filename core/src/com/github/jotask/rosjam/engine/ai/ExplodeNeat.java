package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.SoundAssets;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.entity.BodyEntity;
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

    private final float INRANGE = 2f;

    private final int EXPLOSION_DAMAGE = 4;

    private Network network;

    private Timer timer;

    private final Player player;

    private final float THRESHOLD;

    private boolean exploded;
    private boolean damaged;

    private final BodyEntity entity;

    public ExplodeNeat(final BodyEntity entity) {
        super(entity.getBody());
        this.entity = entity;
        do{
            this.network = Game.get().neatThread.getBestNetwork();
        }while(this.network == null);
        this.THRESHOLD = Game.get().neatThread.getThreshold();
        this.timer = new Timer(.1f);
        this.player = EntityManager.get().getPlayer();
        this.exploded = false;
        this.damaged = false;
    }

    @Override
    public void update() {
        if(timer.isPassed(true)) {
            double[] inputs = getInputs();
            double[] outputs = this.network.evaluate(inputs);
            setOutput(outputs);
            this.dir.scl(SPEED);
            this.body.applyForceToCenter(dir, true);
            this.dir.setZero();
        }

        if(needsExplode()){
            if(!exploded) {
                Rosjam.get().getAssets().getSoundAssets().getSound(SoundAssets.SOUND.EXPLOSION).play();
                this.explode();
                this.exploded = true;
            }
        }

    }

    private boolean needsExplode(){ return (this.body.getPosition().dst2(player.getBody().getPosition()) <  INRANGE); }

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

    private void explode(){
        explode(100, 100f, 1000f, this.body.getPosition().x, this.body.getPosition().y);
        Game.get().getPlay().effects.explode(this.body);
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

        this.entity.kill();

    }

    private void applyBlastImpulse(final Body body, final Vector2 blastCenter, final Vector2 applyPoint, float blastPower){
        Vector2 blastDir = applyPoint.cpy().sub(blastCenter);
        float distance = blastDir.len();
        if(distance == 0) return;
        float invDistance = 1f / distance;
        float impulseMag = Math.min(blastPower * invDistance, blastPower * 0.5f); //Not physically correct

        body.applyLinearImpulse(blastDir.nor().scl(impulseMag), applyPoint, true);

        if(body.getUserData() instanceof Player){
            if(!this.damaged) {
                final Player p = (Player) body.getUserData();
                p.damage(EXPLOSION_DAMAGE);
                this.damaged = true;
            }
        }

    }

}
