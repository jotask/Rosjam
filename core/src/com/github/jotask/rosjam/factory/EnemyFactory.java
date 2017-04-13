package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.rosjam.engine.graphics.Sprite;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.Spawner;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.game.entity.enemy.enemies.*;

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
     *
     *
     * @param room
     * @param type
     * @return
     */
    private static Enemy get(final Room room, final Enemies type){

        final World world = Game.get().getPlay().getWorldManager().getWorld();
        final Body body = BodyFactory.createEnemy(world, .4f);
        final Sprite sprite = SpriteEnemyFactory.get(type, body);

        switch (type){
            case RAT:
                return new Rat(body, sprite, room);
            case SNAKE:
                return new Snake(body, sprite, room);
            case SKELETON:
                return new Skeleton(body, sprite, room);
            case GOBLIN_MAGIC:
                return new GoblinMagic(body, sprite, room);
            case GOBLIN_MELE:
                return new GoblinMele(body, sprite, room);
            case BAT:
                return new Bat(body, sprite, room);
            default:
                return new Bat(body, sprite, room);
        }

    }

}
