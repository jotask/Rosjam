package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.door.NextLevelDoor;
import com.github.jotask.rosjam.game.dungeon.door.RoomDoor;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.util.CollisionFilter;

/**
 * BodyFactory
 *
 * @author Jose Vives Iznardo
 * @since 31/01/2017
 */
public class BodyFactory {

    public static void createDoor(final Door door){
        if(door instanceof RoomDoor){
            createRoomDoor((RoomDoor) door);
        }else if(door instanceof NextLevelDoor){
            createNextLevelDoor((NextLevelDoor)door);
        }else {
            throw new RuntimeException("Unknown Door");
        }
    }

    private static void createNextLevelDoor(NextLevelDoor door) {

        Vector2 p = new Vector2();

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

    private static void createRoomDoor(final RoomDoor door){

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

        CircleShape shape = new CircleShape();
        shape.setRadius(.4f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;
        fd.friction = 15f;
        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.PLAYER);

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        return body;

    }

    public static Body createEnemy(final World world, final float radius){

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;

        Body body = world.createBody(bd);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;
        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.ENEMY);

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        return body;

    }

    public static void createRoom(final Room room){

        World world = Game.get().getPlay().getWorldManager().getWorld();

        BodyDef bd = new BodyDef();
        bd.position.set(room.getCenter());
        bd.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bd);

        float w = Room.WIDTH / 2f;
        float h = Room.HEIGHT / 2f;

        final float off = .5f / 2f;
        final float offset = 1.4f;
        // Top
        createWall(
                body,
                new Vector2(0,+h - (offset / 2f) + off),
                new Vector2(w, (offset / 2f) + off)
        );

        // Bottom
        createWall(
                body,
                new Vector2(0,-h + (offset / 2f) - off),
                new Vector2(w, (offset / 2f) + off)
        );

        // Left
        createWall(
                body,
                new Vector2(+w - (offset / 2f) + off,0),
                new Vector2((offset / 2f) + off, h)
        );

        // Right
        createWall(body,
                new Vector2(-w + (offset / 2f) - off,0),
                new Vector2((offset / 2f) + off, h)
        );

        room.setBody(body);

        body.setUserData(room);
    }

    private static void createWall(Body body, Vector2 pos, Vector2 size){

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x, size.y, pos, 0);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        body.createFixture(fd);
        shape.dispose();

    }

    public static Body createRock(final World world, final Vector2 position){

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(position.x, position.y);

        Body body = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.5f, .5f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;
        CollisionFilter.setMask(fd , CollisionFilter.EENTITY.WALLS);

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        return body;

    }


}
