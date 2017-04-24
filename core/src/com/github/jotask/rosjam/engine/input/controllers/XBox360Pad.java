package com.github.jotask.rosjam.engine.input.controllers;

import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.github.jotask.rosjam.engine.input.Controller;

/**
 * XBox360
 *
 * @author Jose Vives Iznardo
 * @since 24/04/2017
 */
// This code was taken from http://www.java-gaming.org/index.php?topic=29223.0
// With thanks that is!
 /* It seems there are different versions of gamepads with different ID
 Strings.
 * Therefore its IMO a better bet to check for:
 * if (controller.getName().toLowerCase().contains("xbox") &&
 controller.getName().contains("360"))
 *
 * Controller (Gamepad for Xbox 360)
 Controller (XBOX 360 For Windows)
 Controller (Xbox 360 Wireless Receiver for Windows)
 Controller (Xbox wireless receiver for windows)
 XBOX 360 For Windows (Controller)
 Xbox 360 Wireless Receiver
 Xbox Receiver for Windows (Wireless Controller)
 Xbox wireless receiver for windows (Controller)
 */
public class XBox360Pad implements Controller, ControllerListener{

    //public static final String ID = "XBOX 360 For Windows (Controller)";
    public static final int BUTTON_X = 2;
    public static final int BUTTON_Y = 3;
    public static final int BUTTON_A = 0;
    public static final int BUTTON_B = 1;
    public static final int BUTTON_BACK = 6;
    public static final int BUTTON_START = 7;
    public static final PovDirection BUTTON_DPAD_UP = PovDirection.north;
    public static final PovDirection BUTTON_DPAD_DOWN = PovDirection.south;
    public static final PovDirection BUTTON_DPAD_RIGHT = PovDirection.east;
    public static final PovDirection BUTTON_DPAD_LEFT = PovDirection.west;
    public static final int BUTTON_LB = 4;
    public static final int BUTTON_L3 = 8;
    public static final int BUTTON_RB = 5;
    public static final int BUTTON_R3 = 9;
    public static final int AXIS_LEFT_X = 1; //-1 is left | +1 is right
    public static final int AXIS_LEFT_Y = 0; //-1 is up | +1 is down
    public static final int AXIS_LEFT_TRIGGER = 4; //value 0 to 1f
    public static final int AXIS_RIGHT_X = 3; //-1 is left | +1 is right
    public static final int AXIS_RIGHT_Y = 2; //-1 is up | +1 is down
    public static final int AXIS_RIGHT_TRIGGER = 4; //value 0 to -1f

    private final Vector2 direction;
    private final Vector2 shot;

    private boolean left, right, up, down;
    private boolean sLeft, sRight, sUp, sDown;

    private final Vector2 dpad;

    private boolean pause;

    public XBox360Pad() {
        this.direction = new Vector2();
        this.shot = new Vector2();

        this.dpad = new Vector2();

        this.pause = false;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;

        this.sLeft = false;
        this.sRight = false;
        this.sUp = false;
        this.sDown = false;
    }

    @Override
    public Vector2 getDirection() {

        this.direction.add(this.dpad);
        final Vector2 d = new Vector2(this.direction);

        this.direction.setZero();

        if(d.isZero(0.1f)){
            d.setZero();
        }

        return d;
    }

    @Override
    public boolean isShooting() { return sLeft || sRight || sUp || sDown; }

    @Override
    public Vector2 getShootDirection() {
        this.shot.setZero();

        if(this.sLeft) this.shot.x = +1f;
        if(this.sRight) this.shot.x = -1f;
        if(this.sDown) this.shot.y = -1f;
        if(this.sUp) this.shot.y = +1f;

        return this.shot;
    }

    @Override
    public boolean pause() { return this.pause; }

    @Override
    public void connected(com.badlogic.gdx.controllers.Controller controller) { }

    @Override
    public void disconnected(com.badlogic.gdx.controllers.Controller controller) { }

    @Override
    public boolean buttonDown(com.badlogic.gdx.controllers.Controller controller, int buttonCode) {
        if(buttonCode == XBox360Pad.BUTTON_Y) this.sUp = true;
        if(buttonCode == XBox360Pad.BUTTON_A) this.sDown = true;
        if(buttonCode == XBox360Pad.BUTTON_X) this.sRight = true;
        if(buttonCode == XBox360Pad.BUTTON_B) this.sLeft = true;

        if(buttonCode == XBox360Pad.BUTTON_START) this.pause = true;

        return false;
    }

    @Override
    public boolean buttonUp(com.badlogic.gdx.controllers.Controller controller, int buttonCode) {
        if(buttonCode == XBox360Pad.BUTTON_Y) this.sUp = false;
        if(buttonCode == XBox360Pad.BUTTON_A) this.sDown = false;
        if(buttonCode == XBox360Pad.BUTTON_X) this.sRight = false;
        if(buttonCode == XBox360Pad.BUTTON_B) this.sLeft = false;

        if(buttonCode == XBox360Pad.BUTTON_START) this.pause = false;

        return false;

    }

    @Override
    public boolean axisMoved(com.badlogic.gdx.controllers.Controller controller, int axisCode, float value) {

        if(axisCode == XBox360Pad.AXIS_LEFT_X) this.dpad.x = value;
        if(axisCode == XBox360Pad.AXIS_LEFT_Y) this.dpad.y = -value;

        if(axisCode == XBox360Pad.AXIS_RIGHT_X){

        }

        if(axisCode == XBox360Pad.AXIS_RIGHT_Y){

        }


        return false;
    }

    @Override
    public boolean povMoved(com.badlogic.gdx.controllers.Controller controller, int povCode, PovDirection value) {

        // FIXME
        // This is the dpad
        if(value == XBox360Pad.BUTTON_DPAD_LEFT)
            System.out.println("BUTTON_DPAD_LEFT " + value + " " + povCode);

        if(value == XBox360Pad.BUTTON_DPAD_RIGHT)
            System.out.println("BUTTON_DPAD_RIGHT " + value + " " + povCode);

        if(value == XBox360Pad.BUTTON_DPAD_UP)
            System.out.println("BUTTON_DPAD_UP " + value + " " + povCode);

        if(value == XBox360Pad.BUTTON_DPAD_DOWN)
            System.out.println("BUTTON_DPAD_DOWN " + value + " " + povCode);

        return false;
    }

    @Override
    public boolean xSliderMoved(com.badlogic.gdx.controllers.Controller controller, int sliderCode, boolean value) { return false; }

    @Override
    public boolean ySliderMoved(com.badlogic.gdx.controllers.Controller controller, int sliderCode, boolean value) { return false; }

    @Override
    public boolean accelerometerMoved(com.badlogic.gdx.controllers.Controller controller, int accelerometerCode, Vector3 value) { return false; }

}