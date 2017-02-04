package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.engine.ai.ArtificialIntelligence;
import com.github.jotask.rosjam.engine.ai.Spider;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.Enemy;
import com.github.jotask.rosjam.game.world.WorldManager;
import com.github.jotask.rosjam.util.Sprite;

/**
 * EnemyFactory
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
final class EnemyFactory {

    public enum ENEMY{ SPIDER }

    private EnemyFactory(){}

    public static Enemy get(ENEMY type, final Vector2 p, final Room room){

        final WorldManager worldManager = DungeonState.get().getWorldManager();

        final Vector2 center = new Vector2(p);
        center.x += .5f;
        center.y += .5f;

        Body body = BodyFactory.createEnemy(worldManager.getWorld(), center);

        final Sprite sprite = SpriteFactory.getEnemy(ENEMY.SPIDER, body);

        ArtificialIntelligence ai = new Spider(body);

        Enemy enemy = new Enemy(body, ai, sprite, room);
        enemy.getBody().setUserData(enemy);

        EntityManager.add(enemy);

        return enemy;
    }

}
