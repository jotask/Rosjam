package com.github.jotask.rosjam.game.item;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.entity.BodyEntity;
import com.github.jotask.rosjam.util.Timer;

/**
 * Weapon
 *
 * @author Jose Vives Iznardo
 * @since 20/01/2017
 */
public class Weapon extends Item{

    private Timer timer;

    private BodyEntity owner;

    public Weapon(BodyEntity owner) {
        this.owner = owner;

        this.timer = new Timer(.1f);

    }

    private Bullet getBullet(){
        Bullet bullet = EntityFactory.getBullet(owner);
        return bullet;
    }

    public void shot(Vector2 direction){
        if(!timer.isPassed(true)){
            return;
        }
        Bullet b = getBullet();
        b.shot(direction);
    }

}
