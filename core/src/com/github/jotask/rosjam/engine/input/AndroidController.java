package com.github.jotask.rosjam.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.input.android.DirectionController;
import com.github.jotask.rosjam.game.hud.Hud;

/**
 * AndroidController
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class AndroidController implements Controller {

    // TODO Create and improve skin

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

    @Override
    public boolean resetLevel() {

        if(!Gdx.input.justTouched())
            return false;

        int activeTouch = 0;
        for (int i = 0; i < 20; i++) {
            if (Gdx.input.isTouched(i)) activeTouch++;
        }
        return (activeTouch == 2);
    }

}
