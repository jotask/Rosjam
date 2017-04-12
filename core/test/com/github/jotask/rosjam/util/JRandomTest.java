package com.github.jotask.rosjam.util;

import com.badlogic.gdx.math.MathUtils;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * JRandomTest
 *
 * @author Jose Vives Iznardo
 * @since 12/04/2017
 */
public class JRandomTest {

    private final int ATTEMPTS = 100;

    long seed;

    private JRandom random;

    @Before
    public void setUp() throws Exception {
        seed = MathUtils.random(Long.MAX_VALUE);
        this.random = new JRandom(seed);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void random() throws Exception {

        final int FINAL = Integer.MAX_VALUE;

        final Random r = new Random();
        r.setSeed(seed);

        int attemps = ATTEMPTS;

        do{
            final int i = this.random.random(FINAL);
            final int j = r.nextInt(FINAL);

            assertEquals(i, j);

            attemps--;
        }while(attemps <= 0);

    }

    @Test
    public void getRandomEnemy() throws Exception {

        int attemps = ATTEMPTS;

        do{

            final Enemies e = this.random.getRandomEnemy();

            assertNotEquals("is not an enemy", e instanceof Enemies);

            attemps--;
        }while(attemps <= 0);

    }

}