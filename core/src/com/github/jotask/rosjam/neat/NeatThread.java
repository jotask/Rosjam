package com.github.jotask.rosjam.neat;

import com.github.jotask.rosjam.neat.config.Config;
import com.github.jotask.rosjam.neat.config.LoadConfig;
import com.github.jotask.rosjam.neat.jneat.network.Network;

/**
 * NeatThread
 *
 * Neat thread implements runnable interface
 * This is provided to run the neat algorithm in one thread without rendering
 *
 * @author Jose Vives Iznardo
 * @since 28/03/2017
 */
public class NeatThread implements Runnable{

    // The neat algorithm
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

    /**
     * Stop the loop to stop this thread
     */
    public void stop(){ this.isRunning = false; }

    /**
     *  Get the Network from the best enemy on the simulation environment
     *  Is synchronized to avoid threads problems
     * @return The network from the best
     */
    public synchronized Network getBestNetwork(){
        while(this.neat.getJota() == null);
        while(this.neat.getJota().getBest() == null);
        return this.neat.getJota().getBest().getNetwork();
    }

    public synchronized float getThreshold(){ return Float.valueOf(neat.getJota().getConfig().get(Config.Property.THRESHOLD)); }

}
