package com.github.jotask.rosjam.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.engine.Camera;
import com.github.jotask.rosjam.engine.states.State;
import com.github.jotask.rosjam.game.dungeon.room.TestRoom;

/**
 * TestState
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class TestState extends State {

    private TestRoom room;

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float w = 21f;
        float h = 11f;
        this.camera.viewport = new FitViewport(w, h, camera);
        this.camera.viewport.apply();

        TiledMap map = new TmxMapLoader().load("test.tmx");

        room = new TestRoom(this, new Vector2(0,0), map);

        this.camera.translate(room.getCenter());
        this.camera.update();

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.end();
        room.render(sb);
        sb.begin();
    }

    @Override
    public void debug(ShapeRenderer sr) {
        room.debug(sr);
    }

    @Override
    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }

    @Override
    public void dispose() {

    }

}
