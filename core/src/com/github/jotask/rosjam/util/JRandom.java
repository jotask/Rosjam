package com.github.jotask.rosjam.util;

import com.github.jotask.rosjam.game.entity.enemy.Enemies;

import java.util.Random;

/**
 * Generator
 *
 * @author Jose Vives Iznardo
 * @since 31/01/2017
 */
public final class JRandom {

    private final Random random;
    private final long seed;

    public JRandom(long seed) {
        this.seed = seed;
        this.random = new Random();
        this.random.setSeed(seed);
    }

    public int random(int range){
        return random.nextInt(range);
    }

    public Enemies getRandomEnemy(){
        return Enemies.GOBLIN_MAGIC;
    }

    public long getSeed() { return seed; }

}
