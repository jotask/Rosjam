package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.InitialParameters;
import com.github.jotask.rosjam.game.entity.BodyEntity;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.game.entity.player.Player;
import com.github.jotask.rosjam.game.item.Bullet;
import com.github.jotask.rosjam.game.item.Sword;
import com.github.jotask.rosjam.game.item.Weapon;
import com.github.jotask.rosjam.game.world.WorldManager;
import com.github.jotask.rosjam.util.CollisionFilter;
import com.github.jotask.rosjam.engine.graphics.Sprite;

/**
 * EntityBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class EntityFactory {

    private EntityFactory() { }

    public static Player generatePlayer(){
        if(Game.get().getPlay() == null){
            System.out.println("isNull");
        }
        final WorldManager worldManager = Game.get().getPlay().getWorldManager();
        return generatePlayer(worldManager);
    }

    public static Player generatePlayer(final WorldManager worldManager) {

        final Vector2 center = new Vector2();

        final Body body = BodyFactory.createPlayer(worldManager.getWorld(), center);

        final Sprite sprite = SpriteFactory.getPlayer(body);

        Player player = new Player(body, worldManager.getGame().getController(), sprite, InitialParameters.PLAYER_HEALTH_INITIAL);
        player.getBody().setUserData(player);
        return player;

    }

    public static Bullet getBullet(BodyEntity entity){

        final WorldManager worldManager = Game.get().getPlay().getWorldManager();

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

    public static Weapon getWeapon(BodyEntity owner) {
        return new Weapon(owner);
    }

    public static Sword getSword(final BodyEntity owner){

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;

        final Body body = owner.getBody().getWorld().createBody(bd);
        final Sword weapon = new Sword(body, owner);
        body.setUserData(weapon);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
                com.github.jotask.rosjam.neat.engine.weapon.Sword.SIZE.x,
                com.github.jotask.rosjam.neat.engine.weapon.Sword.SIZE.y
        );

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.isSensor = true;

        CollisionFilter.setMask(fd, CollisionFilter.EENTITY.ENEMY_FRIEND);

        Fixture fixture = body.createFixture(fd);
        fixture.setUserData(weapon);

        shape.dispose();

        return weapon;
    }

}
