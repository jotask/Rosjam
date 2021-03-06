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

    public JRandom(long seed) {
        this.random = new Random();
        this.random.setSeed(seed);
    }

    public int random(int range){
        return random.nextInt(range);
    }

    public Enemies getRandomEnemy(){
        final Enemies[] values = Enemies.values();
        return values[random(values.length)];
    }

}
