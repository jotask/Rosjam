package com.github.jotask.rosjam.game.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.Score;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.door.NextLevelDoor;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.HealthEntity;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.game.entity.player.Player;
import com.github.jotask.rosjam.game.item.Bullet;
import com.github.jotask.rosjam.game.item.Sword;

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

        final DungeonState dungeonState = Game.get().getPlay();

        if((a instanceof Player || b instanceof Player) && (a instanceof Enemy || b instanceof Enemy)){
            Player player;
            Enemy enemy;
            if(a instanceof Player){
                player = (Player) a;
                enemy = (Enemy) b;
            }else{
                player = (Player) b;
                enemy = (Enemy) a;
            }
            player.damage(1);
            dungeonState.score.addScore(Score.HIT_BY_ENEMY);
//            enemy.damage(1);
        }

        if((a instanceof Sword || b instanceof Sword) && (a instanceof Player || b instanceof Player)){
            final Sword sword;
            final Player player;
            if(a instanceof Player){
                player = (Player) a;
                sword = (Sword) b;
            }else{
                player = (Player) b;
                sword = (Sword) a;
            }
            player.damage(sword.DMG);
        }

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

                int dmg = bullet.getDamage();
                ent.damage(dmg);

            }
        }

        Object z = contact.getFixtureA().getUserData();
        Object y = contact.getFixtureB().getUserData();

        if(z instanceof Door || y instanceof Door){

            if(z instanceof NextLevelDoor || y instanceof NextLevelDoor){
                dungeonState.getLevel().setCompleted();
                return;
            }

            Door door = null;
            if(z instanceof Door){
                door = (Door)z;
            }else if(y instanceof Door){
                door = (Door)y;
            }

            // TODO
            if(door.self.isCompleted() && !door.isEntered) {
                dungeonState.getLevel().nextRoom(door);
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
