package com.github.jotask.rosjam.engine.input.android;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.game.hud.Hud;

/**
 * KnobController
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class ShootController {

    private Touchpad touchpad;

    private final Vector2 percentage;

    public ShootController(final Hud hud) {

        //Create a touchpad skin
        Skin touchpadSkin = Rosjam.get().getAssets().getSkin();

        //Set background image
        touchpadSkin.add("touchBackground", Rosjam.get().getAssets().getHudAssets().touchBackground);
        //Set knob image
        touchpadSkin.add("touchKnob", Rosjam.get().getAssets().getHudAssets().touchKnob);

        //Create TouchPad Style
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        Drawable touchBackground = touchpadSkin.getDrawable("touchBackground");
        Drawable touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);

        final Stage stage = hud.getStage();
        float x = stage.getWidth() - 100 - 15;
        float y = 15f;

        touchpad.setBounds(x, y, 100, 100);

        //Create a Stage and add TouchPad
        hud.addControl(touchpad);

        this.percentage = new Vector2();

    }

    public final Vector2 getDirection(){
        float x = touchpad.getKnobPercentX();
        float y = touchpad.getKnobPercentY();
        this.percentage.set(x, y);
        return this.percentage;
    }

    public boolean isShooting() {
        return (touchpad.isTouched() && !this.getDirection().isZero(.1f));
    }
}
