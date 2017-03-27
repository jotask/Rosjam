package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Spawner;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.util.Sprite;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * EnemyFactory
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public class EnemyFactory {

    public static Enemy spawn(Room room, Spawner spawner) {

        Enemy enemy = get(room, spawner.type);

        enemy.getBody().setTransform(spawner.position, 0);

        EntityManager.add(enemy);

        return enemy;

    }

    /**
     * http://stackoverflow.com/questions/6094575/creating-an-instance-using-the-class-name-and-calling-constructor
     *
     * @param room
     * @param type
     * @return
     */
    public static Enemy get(final Room room, final Enemies type){

        final World world = DungeonState.get().getWorldManager().getWorld();
        final Body body = BodyFactory.createEnemy(world, .4f);
        final Sprite sprite = SpriteEnemyFactory.get(type, body);

        Class<?> c = null;
        try {
            c = Class.forName(type.aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor<?> cons = c.getConstructors()[0];
        Object object = null;
        try {
             object = cons.newInstance(body, sprite, room);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if(!(object instanceof Enemy))
            throw new RuntimeException("Error spawning enemy");

        return (Enemy) object;
    }

}
