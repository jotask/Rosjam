package com.github.jotask.rosjam.neat.engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.neat.Neat;
import com.github.jotask.rosjam.neat.engine.entity.Entity;
import com.github.jotask.rosjam.neat.engine.entity.Player;
import com.github.jotask.rosjam.neat.engine.weapon.Sword;
import com.github.jotask.rosjam.neat.engine.weapon.Weapon;
import com.github.jotask.rosjam.neat.jneat.NeatEnemy;
import com.github.jotask.rosjam.neat.util.Constant;
import com.github.jotask.rosjam.neat.util.JRandom;

/**
 * Factory
 *
 * @author Jose Vives Iznardo
 * @since 11/02/2017
 */
public class Factory {

    private static Factory instance;

    public static Factory get(){
        return instance;
    }

    private Neat neat;

    public Factory(final Neat neat) {
        this.neat = neat;
        Factory.instance = this;
    }

    public final Player getPlayer(){

        final float radius = .5f;

        final Vector2 p = JRandom.randomPositionPlayer();
        final Body body = createBody(p.x, p.y);

        Fixture playerBody = createEntityBody(body, radius);

        Player player = new Player(body);
        playerBody.setUserData(player);

        body.setUserData(Entity.Type.PLAYER);

        CollisionFilter.setMask(playerBody, CollisionFilter.ENTITY.PLAYER);

        return player;


    }

    public final NeatEnemy getNeatEnemy(final float threshold){

        final Vector2 p = JRandom.randomPosition();
        float radius = .5f;

        final Body body = createBody(p.x, p.y);

        Fixture enemyBody = createEntityBody(body, radius);

        NeatEnemy enemy = new NeatEnemy(body, threshold);

        body.setUserData(Entity.Type.ENEMY);

        enemyBody.setUserData(enemy);

        EntityManager.add(enemy);

        CollisionFilter.setMask(enemyBody, CollisionFilter.ENTITY.ENEMY);

        return enemy;

    }

    public Weapon getSword(){

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;

        final Body body = createBody(0,0);
        final Weapon weapon = new Sword(body);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Sword.SIZE.x, Sword.SIZE.y);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.isSensor = true;

        Fixture fixture = body.createFixture(fd);
        fixture.setUserData(weapon);

        CollisionFilter.setMask(fixture, CollisionFilter.ENTITY.ENEMY_FRIEND);

        shape.dispose();
        return weapon;

    }

    public void createWalls(){
        float WIDTH = Constant.WORLD_WIDTH / 2f;
        float HEIGHT = Constant.WORLD_HEIGHT / 2f;

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;

        Vector2[] vertices = new Vector2[]{
                new Vector2(-WIDTH, -HEIGHT),
                new Vector2(WIDTH, -HEIGHT),
                new Vector2(WIDTH, HEIGHT),
                new Vector2(-WIDTH, HEIGHT)
        };

        Body body = neat.getWorld().createBody(bd);

        ChainShape shape = new ChainShape();
        shape.createLoop(vertices);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        Fixture f = body.createFixture(fd);

        CollisionFilter.setMask(f, CollisionFilter.ENTITY.WALLS);

    }

    private Body createBody(float x, float y){

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(x, y);

        final World world = neat.getWorld();

        Body body = null;
        while(body == null){
            if(world.isLocked()) {
                System.out.println("isLocked");
                continue;
            }
            body = world.createBody(bd);
        }

        return body;

    }

    private Fixture createEntityBody(final Body body, float radius){

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        Fixture fix = body.createFixture(fd);

        shape.dispose();

        return fix;

    }

}
