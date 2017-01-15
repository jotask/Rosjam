package com.github.jotask.rosjam.game.controller.android;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.github.jotask.rosjam.game.hud.Hud;

/**
 * KnobController
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class DirectionController {

    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;

    private final Vector2 percentage;

    public DirectionController(final Hud hud) {

        // TODO Replace the texture for the version with assets

        //Create a touchpad skin
        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));

        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(15, 15, 200, 200);

        //Create a Stage and add TouchPad
        hud.addControl(touchpad);

        this.percentage = new Vector2();

    }

    public final Vector2 getMovement(){
        float x = touchpad.getKnobPercentX();
        float y = touchpad.getKnobPercentY();
        this.percentage.set(x, y);
        return this.percentage;
    }

}
