package com.github.jotask.rosjam.game.controller;

import com.badlogic.gdx.math.Vector2;

/**
 * Controller
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public interface Controller {

    Vector2 getDirection();

    boolean isShooting();

}