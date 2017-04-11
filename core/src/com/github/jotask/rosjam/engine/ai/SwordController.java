package com.github.jotask.rosjam.engine.ai;

import com.github.jotask.rosjam.game.item.Sword;

/**
 * WeaponController
 *
 * @author Jose Vives Iznardo
 * @since 11/04/2017
 */
public class SwordController {

    private final Sword weapon;

    public SwordController(final Sword weapon) { this.weapon = weapon; }

    public void left(){ weapon.shotDirection.set(-1, 0); }

    public void right(){ weapon.shotDirection.set(1, 0); }

    public void up(){ weapon.shotDirection.set(0, -1); }

    public void down(){ weapon.shotDirection.set(0, 1); }

}
