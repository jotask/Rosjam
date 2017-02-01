package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.Enemy;
import com.github.jotask.rosjam.util.CollisionFilter;

/**
 * BodyFactory
 *
 * @author Jose Vives Iznardo
 * @since 31/01/2017
 */
class BodyFactory {

    public static void createDoor(final Door door){

        Vector2 p = new Vector2();

        float w = 9.28f;
        float h = 4.28f;

        switch (door.side){
            case DOWN:
                p.y += h - .4f;
                p.y += .5f;
                break;
            case RIGHT:
                p.x += w - .4f;
                p.x += .5f;
                break;
            case UP:
                p.y -= h;
                break;
            case LEFT:
                p.x -= w;
                break;
            default:
                throw new RuntimeException("Unknown Door type");
        }

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.5f, .5f, p, 0f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.isSensor = true;
        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.DOOR);

        Body body = door.self.getBody();
        Fixture f = body.createFixture(fd);
        f.setUserData(door);

    }

    public static Body createPlayer(final World world, final Vector2 position){

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(position.x, position.y);

        Body body = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.5f, .5f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;
        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.PLAYER);

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        return body;

    }

    public static void createEnemy(Enemy enemy){

//        BodyDef bd = new BodyDef();
//        bd.type = BodyDef.BodyType.DynamicBody;
//        bd.position.set(center.x, center.y);
//
//        Body body = worldManager.getWorld().createBody(bd);
//
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(.5f, .5f);
//
//        FixtureDef fd = new FixtureDef();
//        fd.shape = shape;
//        fd.density = 1f;
//        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.ENEMY);
//
//        Fixture fixture = body.createFixture(fd);
//
//        shape.dispose();

    }

    public static void createRoom(final Room room){

        World world = DungeonState.get().getWorldManager().getWorld();

        BodyDef bd = new BodyDef();
        bd.position.set(room.getCenter());
        bd.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bd);

        float w = Room.WIDTH / 2f;
        float h = Room.HEIGHT / 2f;

        final float offset = 1.4f;

//        Array<Vector2> vertices = new Array<Vector2>();
//
//        Vector2 bone = new Vector2(-w, -h);
//        Vector2 btwo = new Vector2(-w, h);
//        Vector2 bthree = new Vector2(w, -h);
//        Vector2 bfour = new Vector2(w, h);
//
//        vertices.spawn(bone);
//        vertices.spawn(btwo);
//        vertices.spawn(bthree);
//        vertices.spawn(bfour);
//
//        Vector2 one = new Vector2(-w + offset, -h + offset);
//        Vector2 two = new Vector2(-w + offset, h - offset);
//        Vector2 three = new Vector2(w - offset, -h + offset);
//        Vector2 four = new Vector2(w - offset, h - offset);
//
//        vertices.spawn(one);
//        vertices.spawn(two);
//        vertices.spawn(three);
//        vertices.spawn(four);
//
//        Vector2[] v = new Vector2[vertices.size];
//        for(int i = 0; i < v.length; i++){
//            v[i] = vertices.get(i);
//        }

        // top
        {
            // TODO
//            Vector2 p = new Vector2(
//            );
//            PolygonShape shape = new PolygonShape();
//            shape.setAsBox(w, offset, p, 0);
//            FixtureDef fd = new FixtureDef();
//            fd.shape = shape;
//            body.createFixture(fd);
//            shape.dispose();
        }

//        createEdgeFixture(body, one, two);
//        createEdgeFixture(body, two, four);
//        createEdgeFixture(body, three, one);
//        createEdgeFixture(body, four, three);

        room.setBody(body);

        body.setUserData(room);
    }



}
