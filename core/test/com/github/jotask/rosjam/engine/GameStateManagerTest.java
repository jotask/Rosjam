package com.github.jotask.rosjam.engine;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.jotask.rosjam.test.TestState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GameStateManagerTest
 *
 * @author Jose Vives Iznardo
 * @since 12/04/2017
 */
public class GameStateManagerTest extends GameTest{

    GameStateManager gsm;

    @Before
    public void setUp() throws Exception { this.gsm = new GameStateManager(); }

    @After
    public void tearDown() throws Exception { this.gsm.dispose(); }

    @Test
    public void changeState() throws Exception {

        this.gsm.changeState(GameStateManager.STATE.TEST);

        assertNotNull("new state is null", this.gsm.getState());

        assertTrue("Is not the state choose", (this.gsm.getState() instanceof TestState));

    }

    @Test
    public void resize() throws Exception {

        this.gsm.changeState(GameStateManager.STATE.TEST);

        final Viewport v = this.gsm.getState().getCamera().getViewport();

        final int oldW = (int) v.getWorldWidth();
        final int oldH = (int) v.getWorldHeight();

        final int newW = (int) (oldW * .5f);
        final int newH = (int) (oldH * .5f);

        this.gsm.resize( newW, newH);

        assertTrue("viewport width is not changed",  (oldW != (v.getScreenWidth())));
        assertTrue("viewport height is not changed", (oldH != (v.getScreenHeight())));

    }

}