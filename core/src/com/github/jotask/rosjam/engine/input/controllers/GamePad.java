package com.github.jotask.rosjam.engine.input.controllers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;

/**
 * GamePad
 *
 * @author Jose Vives Iznardo
 * @since 24/04/2017
 */
public class GamePad {

    private boolean isSupported;

    private com.github.jotask.rosjam.engine.input.Controller controller;

    public GamePad() {

        final Array<Controller> controllers = Controllers.getControllers();
        for(Controller c: controllers){
            if(c.getName().toLowerCase().contains("xbox") && c.getName().contains("360")){

                System.out.println(c.getName());

                this.isSupported = true;
                this.controller = new XBox360Pad();

                Controllers.addListener((ControllerListener) this.controller);

                break;

            }
        }

    }

    public boolean isSupported(){ return this.isSupported; }

    public com.github.jotask.rosjam.engine.input.Controller getController(){ return this.controller; }

}
