package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.entity.enemy.enemies.GoblinMele;
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

    private final float SPEED = 25f;

    private Network network;

    private final Timer timer;

    private final SwordController swordController;

    private final float THRESHOLD;

    private final GoblinMele goblinMele;

    public Neat(final GoblinMele goblinMele) {
        super(goblinMele.getBody());
        this.goblinMele = goblinMele;
        do{
            this.network = Game.get().neatThread.getBestNetwork();
        }while(this.network == null);
        this.THRESHOLD = Game.get().neatThread.getThreshold();
        this.timer = new Timer(.1f);
        this.swordController = new SwordController(this.goblinMele.sword);
    }

    @Override
    public void update() {
        if(timer.isPassed(true)) {
            final double[] inputs = getInputs();
            final double[] outputs = this.network.evaluate(inputs);
            setOutput(outputs);
            this.dir.scl(SPEED);
            this.body.applyForceToCenter(dir, true);
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

        if(output[Constants.Outputs.w_left.ordinal()] > THRESHOLD) {
            this.swordController.left();
        }
        if(output[Constants.Outputs.w_right.ordinal()] > THRESHOLD) {
            this.swordController.right();
        }
        if(output[Constants.Outputs.w_up.ordinal()] > THRESHOLD) {
            this.swordController.up();
        }
        if(output[Constants.Outputs.w_down.ordinal()] > THRESHOLD) {
            this.swordController.down();
        }

    }

}
