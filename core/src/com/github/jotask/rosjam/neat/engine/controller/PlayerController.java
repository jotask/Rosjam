package com.github.jotask.rosjam.neat.engine.controller;

import com.github.jotask.rosjam.neat.engine.entity.Player;
import com.github.jotask.rosjam.neat.util.JRandom;

/**
 * PlayerController
 *
 * @author Jose Vives Iznardo
 * @since 24/02/2017
 */
public class PlayerController {

    private final Player player;

    public PlayerController(Player enemy) {
        this.player = enemy;
    }

    public void moveRandom(){ player.velocity.set(JRandom.getDir()); }

    public void left(){ player.velocity.set(-1, 0); }

    public void right(){ player.velocity.set(1, 0); }

    public void up(){ player.velocity.set(0, 1); }

    public void down(){ player.velocity.set(0, -1); }

}
