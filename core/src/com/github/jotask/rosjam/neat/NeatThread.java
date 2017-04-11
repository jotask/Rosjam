package com.github.jotask.rosjam.neat;

import com.github.jotask.rosjam.neat.config.Config;
import com.github.jotask.rosjam.neat.config.LoadConfig;
import com.github.jotask.rosjam.neat.jneat.network.Network;

/**
 * NeatThread
 *
 * @author Jose Vives Iznardo
 * @since 28/03/2017
 */
public class NeatThread implements Runnable{

    private Neat neat;

    private boolean isRunning;

    public NeatThread() {
        this.isRunning = true;
    }

    @Override
    public void run() {

        final Config cfg = LoadConfig.load(false);

        this.neat = new Neat(cfg);
        while(this.isRunning) {
            this.neat.update();
        }
        this.neat.dispose();
    }

    public void stop(){
        this.isRunning = false;
    }

    public synchronized Network getBestNetwork(){
        Network network = this.neat.getJota().getBest().getNetwork();
        return network;
    }

    public synchronized float getThreshold(){ return new Float(neat.getJota().getConfig().get(Config.Property.THRESHOLD)); }

}
