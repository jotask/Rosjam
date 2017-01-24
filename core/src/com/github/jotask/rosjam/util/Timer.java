package com.github.jotask.rosjam.util;

/**
 * Timer
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public class Timer {

    private final long time;
    private long next;

    public Timer(float seg){

        time = this.toSec(seg);

        reset();
    }

    private long toSec(float seg){

        Float f = seg;

        f *= 1000000000;

        return f.longValue();

    }

    public void reset(){
        this.next = System.nanoTime() + time;
    }

    public boolean isPassed(){ return this.isPassed(false);}
    public boolean isPassed(boolean reset){

        boolean b = (System.nanoTime() > this.next);

        if(reset && b)
            reset();

        return b;

    }

}
