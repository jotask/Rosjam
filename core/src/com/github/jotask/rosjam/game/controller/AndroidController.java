package com.github.jotask.rosjam.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.controller.android.DirectionController;
import com.github.jotask.rosjam.game.hud.Hud;

/**
 * AndroidController
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class AndroidController implements Controller {

    private DirectionController direction;

    public AndroidController(final Hud stage) {
        this.direction = new DirectionController(stage);
    }

    @Override
    public Vector2 getDirection() { return this.direction.getMovement(); }

    @Override
    public boolean isShooting() {
        return false;
    }

}
