package com.github.jotask.rosjam.game.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.dungeon.door.Door;
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

        if(a instanceof Bullet || b instanceof Bullet) {

            if (a instanceof Bullet || b instanceof Bullet && a instanceof HealthEntity || b instanceof HealthEntity) {

                if (a instanceof Room || b instanceof Room)
                    return;

                if (a instanceof HealthEntity && b instanceof HealthEntity)
                    return;

                Bullet bullet = null;
                HealthEntity ent = null;
                if (a instanceof Bullet) {
                    bullet = (Bullet) a;
                } else {
                    bullet = (Bullet) b;
                }

                if (a instanceof HealthEntity) {
                    ent = (HealthEntity) a;
                } else {
                    ent = (HealthEntity) b;
                }

                float dmg = bullet.getDamage();
                ent.damage(dmg);

            }
        }

        Object z = contact.getFixtureA().getUserData();
        Object y = contact.getFixtureB().getUserData();

        if(z instanceof Door || y instanceof Door){

            Door door = null;
            if(z instanceof Door){
                door = (Door)z;
            }else if(y instanceof Door){
                door = (Door)y;
            }

            // TODO
            if(door.self.isCompleted() && !door.isEntered) {
                DungeonState.get().getLevel().nextRoom(door);
                door.connected.isEntered = true;
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

        Object z = contact.getFixtureA().getUserData();
        Object y = contact.getFixtureB().getUserData();

        if(z instanceof Door || y instanceof Door){

            Door door = null;
            if(z instanceof Door){
                door = (Door)z;
            }else if(y instanceof Door){
                door = (Door)y;
            }

            // TODO
            if( door.self.isCompleted()) {
                door.setOpen(true);
                door.isEntered = false;

                System.out.println("endContact()");

            }

        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
