package com.github.jotask.rosjam.engine.input;

import com.badlogic.gdx.Input;

/**
 * DesktopInput
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public enum DesktopInput {

    UP(Input.Keys.W),
    LEFT(Input.Keys.A),
    DOWN(Input.Keys.S),
    RIGHT(Input.Keys.D),
    SHOT_UP(Input.Keys.UP),
    SHOT_LEFT(Input.Keys.LEFT),
    SHOT_DOWN(Input.Keys.DOWN),
    SHOT_RIGHT(Input.Keys.RIGHT);

    final int key;

    DesktopInput(int key){
        this.key = key;
    }

}
