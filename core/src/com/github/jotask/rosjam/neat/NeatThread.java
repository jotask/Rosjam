package com.github.jotask.rosjam.neat;

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
        this.neat = new Neat();
        while(this.isRunning) {
            this.neat.update();
        }
        this.neat.dispose();
    }

    public void stop(){
        this.isRunning = false;
    }

}
