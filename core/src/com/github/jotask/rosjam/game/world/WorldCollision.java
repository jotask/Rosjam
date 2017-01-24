package com.github.jotask.rosjam.game.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.HealthEntity;
import com.github.jotask.rosjam.game.item.Bullet;

/**
 * WorldCollision
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
class WorldCollision implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Object a = contact.getFixtureA().getBody().getUserData();
        Object b = contact.getFixtureB().getBody().getUserData();

        if(a instanceof Bullet || b instanceof Bullet && a instanceof HealthEntity || b instanceof HealthEntity){

            if(a instanceof Room || b instanceof Room)
                return;

            if(a instanceof HealthEntity && b instanceof HealthEntity)
                return;

            Bullet bullet = null;
            HealthEntity ent = null;
            if(a instanceof Bullet){
                bullet = (Bullet)a;
            }else{
                bullet = (Bullet)b;
            }

            if(a instanceof HealthEntity){
                ent = (HealthEntity)a;
            }else{
                ent = (HealthEntity)b;
            }

            float dmg = bullet.getDamage();
            ent.damage(dmg);

        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
