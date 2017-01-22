package com.github.jotask.rosjam.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import static com.github.jotask.rosjam.engine.input.DesktopInput.*;

/**
 * DesktopController
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DesktopController implements Controller {

    Vector2 direction;
    Vector2 shoot;

    public DesktopController() {
        this.direction = new Vector2();
        this.shoot = new Vector2();
    }

    @Override
    public Vector2 getDirection() {

        direction.setZero();

        if(Gdx.input.isKeyPressed(LEFT.key)){
            direction.x -= 1f;
        }else if(Gdx.input.isKeyPressed(RIGHT.key)){
            direction.x += 1f;
        }

        if(Gdx.input.isKeyPressed(UP.key)){
            direction.y += 1f;
        }else if(Gdx.input.isKeyPressed(DOWN.key)){
            direction.y -= 1f;
        }

        return this.direction;

    }

    @Override
    public boolean resetLevel() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    @Override
    public boolean isShooting() {
        return  (Gdx.input.isKeyPressed(SHOT_UP.key) ||
                Gdx.input.isKeyPressed(SHOT_LEFT.key) ||
                Gdx.input.isKeyPressed(SHOT_DOWN.key) ||
                Gdx.input.isKeyPressed(SHOT_RIGHT.key));
    }

    @Override
    public Vector2 getShootDirection() {

        shoot.setZero();

        if(Gdx.input.isKeyPressed(SHOT_RIGHT.key)){
            this.shoot.x = +1f;
        }else if(Gdx.input.isKeyPressed(SHOT_LEFT.key)){
            this.shoot.x = -1f;
        }

        if(Gdx.input.isKeyPressed(SHOT_UP.key)){
            this.shoot.y = +1f;
        }else if(Gdx.input.isKeyPressed(SHOT_DOWN.key)){
            this.shoot.y = -1f;
        }

        return shoot;
    }
}
