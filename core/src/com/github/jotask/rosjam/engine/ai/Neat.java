package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.neat.jneat.network.Network;
import com.github.jotask.rosjam.neat.jneat.util.Constants;
import com.github.jotask.rosjam.util.Timer;

/**
 * Neat
 *
 * @author Jose Vives Iznardo
 * @since 07/04/2017
 */
public class Neat extends ArtificialIntelligence {

    public final float SPEED = 10f;

    private final Network network;

    private Timer timer;

    private final float THRESHOLD;

    public Neat(Body body) {
        super(body);
        this.network = Game.get().neatThread.getBestNetwork();
        this.THRESHOLD = Game.get().neatThread.getThreshold();
        this.timer = new Timer(1f);
    }

    @Override
    public void update() {
        if(timer.isPassed(true)) {
            setOutput(network.evaluate(getInputs()));
            this.body.applyForceToCenter(dir.scl(SPEED), true);
            this.dir.setZero();
        }
    }

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
        // TODO add weapons to neat enemies
//        if(output[Constants.Outputs.w_left.ordinal()] > THRESHOLD) {
//            this.weaponController.left();
//        }
//        if(output[Constants.Outputs.w_right.ordinal()] > THRESHOLD) {
//            this.weaponController.right();
//        }
//        if(output[Constants.Outputs.w_up.ordinal()] > THRESHOLD) {
//            this.weaponController.up();
//        }
//        if(output[Constants.Outputs.w_down.ordinal()] > THRESHOLD) {
//            this.weaponController.down();
//        }

    }

}
