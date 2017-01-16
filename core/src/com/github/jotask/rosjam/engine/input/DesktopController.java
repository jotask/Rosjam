package com.github.jotask.rosjam.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * DesktopController
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DesktopController implements Controller {

    Vector2 direction;

    public DesktopController() {
        this.direction = new Vector2();
    }

    @Override
    public Vector2 getDirection() {

        direction.setZero();

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            direction.x -= 1f;
        }else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            direction.x += 1f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            direction.y += 1f;
        }else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            direction.y -= 1f;
        }

        return this.direction;

    }

    @Override
    public boolean isShooting() {
        return false;
    }

}
