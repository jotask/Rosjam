package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.engine.ai.ArtificialIntelligence;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.PlayerAssets;
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

    public static Player generatePlayer(final Room room) {

        final WorldManager worldManager = DungeonState.get().getWorldManager();

        final Vector2 center = room.getCenter();

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(center.x, center.y);

        Body body = worldManager.getWorld().createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.5f, .5f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;
        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.PLAYER);

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        TextureRegion region = Rosjam.get().getAssets().getPlayerAssets().getRegion(PlayerAssets.SPRITE.DEFAULT);

        final Sprite sprite = new Sprite(region, body);

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

        Bullet bullet = new Bullet(body);
        bullet.getBody().setUserData(bullet);

        EntityManager.add(bullet);

        return bullet;

    }

    public static Enemy createEnemy(final Vector2 p){

        final WorldManager worldManager = DungeonState.get().getWorldManager();

        final Vector2 center = new Vector2(p);
        center.x += .5f;
        center.y += .5f;

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(center.x, center.y);

        Body body = worldManager.getWorld().createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.5f, .5f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;
        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.ENEMY);

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        TextureRegion region = Rosjam.get().getAssets().getPlayerAssets().getRegion(PlayerAssets.SPRITE.DEFAULT);

        final Sprite sprite = new Sprite(region, body);

        ArtificialIntelligence ai = new ArtificialIntelligence();

        Enemy enemy = new Enemy(body, ai, sprite);
        enemy.getBody().setUserData(enemy);

        return enemy;
    }

    public static Weapon getWeapon(BodyEntity owner) {
        return new Weapon(owner);
    }
}
