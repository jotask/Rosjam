package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.BodyEntity;
import com.github.jotask.rosjam.game.entity.Enemy;
import com.github.jotask.rosjam.game.entity.Player;
import com.github.jotask.rosjam.game.item.Bullet;
import com.github.jotask.rosjam.game.item.Weapon;
import com.github.jotask.rosjam.game.world.WorldManager;
import com.github.jotask.rosjam.util.CollisionFilter;
import com.github.jotask.rosjam.util.Sprite;

/**
 * EntityBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class EntityFactory {

    private EntityFactory() { }

    public static Player generatePlayer() {

        final WorldManager worldManager = DungeonState.get().getWorldManager();

        final Vector2 center = new Vector2();

        final Body body = BodyFactory.createPlayer(worldManager.getWorld(), center);

        final Sprite sprite = SpriteFactory.getPlayer(body);

        Player player = new Player(body, worldManager.getGame().getController(), sprite);
        player.getBody().setUserData(player);
        return player;

    }

    public static Bullet getBullet(BodyEntity entity){

        final WorldManager worldManager = DungeonState.get().getWorldManager();

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(entity.getBody().getPosition());

        Body body = worldManager.getWorld().createBody(bd);

        Shape shape = new CircleShape();
        shape.setRadius(0.25f);

        FixtureDef fd = new FixtureDef();

        if(entity instanceof Player){
            CollisionFilter.setMask(fd, CollisionFilter.EENTITY.PLAYER_FRIEND);
        }
        else if(entity instanceof Enemy){
            CollisionFilter.setMask(fd, CollisionFilter.EENTITY.ENEMY_FRIEND);
        }

        fd.shape = shape;

        body.createFixture(fd);

        shape.dispose();

        final Sprite sprite = SpriteFactory.getBullet(body);

        Bullet bullet = new Bullet(body, sprite);
        bullet.getBody().setUserData(bullet);

        EntityManager.add(bullet);

        return bullet;

    }

    public static Enemy createEnemy(final Vector2 p, final Room room){
        return EntityFactory.createEnemy(EnemyFactory.ENEMY.SPIDER, p, room);
    }

    public static Enemy createEnemy(final EnemyFactory.ENEMY enemy, final Vector2 p, final Room room){
        Enemy e = EnemyFactory.get(enemy, p, room);
        e.update();
        return e;
    }

    public static Weapon getWeapon(BodyEntity owner) {
        return new Weapon(owner);
    }

}
