package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.engine.map.MapTiled;
import com.github.jotask.rosjam.game.world.WorldManager;
import com.github.jotask.rosjam.util.CollisionFilter;

/**
 * Box2DFactory
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
class Box2DFactory {

    public static void createBodies(final MapTiled map, final WorldManager worldManager){

        // TODO Calculate this ppt
        final float ppt = 15.375f;

        MapObjects collisionLayer = map.getMap().getLayers().get("wall").getObjects();

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(map.getPosition());
        final Body body = worldManager.getWorld().createBody(bd);

        for(MapObject obj: collisionLayer){

            if (obj instanceof TextureMapObject) {
                continue;
            }

            Shape shape = null;

            if (obj instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject)obj, ppt);
            }
            else if (obj instanceof PolygonMapObject) {
                System.out.println("PolygonMapObject");
            }
            else if (obj instanceof PolylineMapObject) {
                shape = getPolyMap((PolylineMapObject)obj, ppt);
            }
            else if (obj instanceof CircleMapObject) {
                System.out.println("CircleMap");
            }
            else {
                continue;
            }

            if(shape == null){
                System.out.println("Shape Is Null");
                continue;
            }

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            CollisionFilter.setMask(fd, CollisionFilter.EENTITY.WALLS);

            body.createFixture(fd);

            shape.dispose();

        }

    }

    private static Shape getPolyMap(PolylineMapObject obj, final float ppt) {
        float[] vertices = obj.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / ppt;
            worldVertices[i].y = vertices[i * 2 + 1] / ppt;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }

    private static Shape getRectangle(RectangleMapObject obj, final float ppt){

        Rectangle rectangle = obj.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / ppt,
                (rectangle.y + rectangle.height * 0.5f ) / ppt);
        polygon.setAsBox(rectangle.width * 0.5f / ppt,
                rectangle.height * 0.5f / ppt,
                size,
                0.0f);

        return polygon;

    }

}
