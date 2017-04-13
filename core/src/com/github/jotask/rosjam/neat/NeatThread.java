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

    private final Neat neat;

    private boolean isRunning;

    public NeatThread() {
        this.isRunning = true;

        final Config cfg = LoadConfig.load(false);
        this.neat = new Neat(cfg);
    }

    @Override
    public void run() {

        while(this.isRunning) {
            this.neat.update();
        }
        this.neat.dispose();
    }

    public void stop(){ this.isRunning = false; }

    public synchronized Network getBestNetwork(){
        while(this.neat.getJota() == null);
        while(this.neat.getJota().getBest() == null);
        return this.neat.getJota().getBest().getNetwork();
    }

    public synchronized float getThreshold(){ return Float.valueOf(neat.getJota().getConfig().get(Config.Property.THRESHOLD)); }

}
