package com.github.jotask.rosjam.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.input.android.DirectionController;
import com.github.jotask.rosjam.engine.input.android.ShootController;
import com.github.jotask.rosjam.game.hud.Hud;

/**
 * AndroidController
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class AndroidController implements Controller {

    private DirectionController direction;
    private ShootController shoot;

    AndroidController(final Hud stage) {
        this.direction = new DirectionController(stage);
        this.shoot = new ShootController(stage);
    }

    @Override
    public Vector2 getDirection() { return this.direction.getMovement(); }

    @Override
    public boolean isShooting() {
        return shoot.isShooting();
    }

    @Override
    public Vector2 getShootDirection() {
        return shoot.getDirection().nor();
    }

    @Override
    public boolean pause() { return Gdx.input.isKeyJustPressed(Input.Keys.BACK); }

}
