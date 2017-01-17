package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.map.MapTiled;
import com.github.jotask.rosjam.game.dungeon.config.ConfigRoom;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * RoomBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
class RoomFactory {

    public static Room generateRoom(ConfigRoom cfg) {
        TiledMap tiledMap = new TmxMapLoader().load("test.tmx");
        Camera camera = cfg.worldManager.getGame().getCamera();
        MapTiled map = new MapTiled(cfg.position, tiledMap, camera);
        Room room = new Room(cfg.position, map, calculateBounds(map));
        Box2DFactory.createBodies(map, cfg.worldManager);
        return room;
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
