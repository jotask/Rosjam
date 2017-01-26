package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.map.MapTiled;
import com.github.jotask.rosjam.game.dungeon.config.ConfigRoom;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import static com.github.jotask.rosjam.game.dungeon.door.Door.SIDE.*;

/**
 * RoomBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
class RoomFactory {

    enum FILE{
        TEST("test"),
        ONE("one"),
        TWO("two"),
        THREE("three");

        final String file;

        FILE(String f){
            file = f;
        }
    }

    public static Room generateRoom(ConfigRoom cfg) {

        final FILE[] val = FILE.values();
        int i = MathUtils.random(val.length - 1 );

//        TiledMap tiledMap = new TmxMapLoader().load(val[i].file + ".tmx ");

        String file = val[i].file+".tmx";

        TiledMap tiledMap = new TmxMapLoader().load(file);
        Camera camera = cfg.worldManager.getGame().getCamera();
        MapTiled map = new MapTiled(cfg.position, tiledMap, camera);
        Room room = new Room(cfg.position, map, calculateBounds(map));
        Body body = Box2DFactory.createBody(room, cfg.worldManager);
        room.setWalls(body);
        body.setUserData(room);

        spawners(room, map);

        return room;

    }

    private static void spawners(Room room, MapTiled map) {

        MapLayer layer = map.getMap().getLayers().get("spawn");
        MapObjects objects = layer.getObjects();

        for(MapObject o: objects){
            final MapProperties prop = o.getProperties();
            float x = prop.get("x", Float.class);
            float y = prop.get("y", Float.class);
            float w = prop.get("width", Float.class);
            float h = prop.get("height", Float.class);
            Vector2 s = new Vector2(room.getPosition());
            s.x += (x / w);
            s.y += (y / h);
            room.spawners.add(s);

        }

    }

    private static void replaceWithWall(final Room room, final Door door){

        Vector2 pos = new Vector2(room.getCenter());
        Vector2 size = new Vector2();
        size.x = .5f;
        size.y = .5f;

        if((door.getSide() == LEFT) || (door.getSide() == RIGHT)){
            size.x *= 3f;
            if(door.getSide() == LEFT){
                pos.y -= 5f;
            }else if(door.getSide() == RIGHT){
                pos.y += 5;
            }
        }else if((door.getSide() == UP) || (door.getSide() == DOWN)){
            size.y *= 3f;
            if(door.getSide() == UP){
                pos.x -= 10f;
            }else if(door.getSide() == DOWN){
                pos.x += 10f;
            }
        }

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x, size.y, pos, 0);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        if(door.connected != null)
            fd.isSensor = true;

        room.getWalls().createFixture(fd);

    }

    private static Rectangle calculateBounds(final MapTiled map){

        Rectangle bounds = new Rectangle();
        bounds.setPosition(map.getPosition());

        MapProperties prop = map.getMap().getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        float x  = ((mapWidth  * tilePixelWidth )) * MapTiled.SCALE;
        float y  = ((mapHeight * tilePixelHeight)) * MapTiled.SCALE;

        bounds.setSize(x, y);

        return bounds;
    }

}
