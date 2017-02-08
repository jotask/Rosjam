package com.github.jotask.rosjam.game;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;

/**
 * Spawner
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class Spawner {

    public final Vector2 position;
    public final Enemies type;

    public Spawner(Enemies type, final Vector2 position) {
        this.position = position;
        this.type = type;
    }

}
