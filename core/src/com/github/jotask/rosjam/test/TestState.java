package com.github.jotask.rosjam.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.input.DesktopController;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.factory.BodyFactory;
import com.github.jotask.rosjam.factory.SpriteEnemyFactory;
import com.github.jotask.rosjam.factory.SpriteFactory;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;
import com.github.jotask.rosjam.game.entity.enemy.enemies.GoblinMagic;
import com.github.jotask.rosjam.game.entity.player.Player;
import com.github.jotask.rosjam.util.Sprite;

/**
 * TestState
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class TestState extends CameraState {

    private World world;
    private Box2DDebugRenderer renderer;

    private Player player;

    private GoblinMagic enemy;

    final Rectangle room;

    public TestState(Camera camera) {
        super(camera);
        world = new World(new Vector2(0,0), true);
        this.renderer = new Box2DDebugRenderer();
        {
            this.room = new Rectangle();
            this.room.setPosition(0,0);
            this.room.setSize(21f, 11f);
            createBounds();
        }
        {
            final Vector2 center = new Vector2(-5, 0);
            final Body body = BodyFactory.createPlayer(world, center);
            final Sprite sprite = SpriteFactory.getPlayer(body);
            this.player = new Player(body, new DesktopController(), sprite);
            this.player.getBody().setUserData(player);
            this.player.getBody().setTransform(10.5f, 5.5f, 0);

        }
        {
            final Body body = BodyFactory.createEnemy(world, .4f);
            final Sprite sprite = SpriteEnemyFactory.get(Enemies.GOBLIN_MAGIC, body);
            this.enemy = new GoblinMagic(body, sprite, null);
            this.enemy.getBody().setTransform(12.5f, 5.5f, 0);
        }
    }

    @Override
    public void update() {

        this.camera.position.set(this.player.getBody().getPosition(), 0);
        this.camera.update();

        this.world.step(Gdx.graphics.getDeltaTime(), 6, 3);
        this.player.update();
        this.enemy.update();
    }

    @Override
    public void render(SpriteBatch sb) {}

    @Override
    public void debug(ShapeRenderer sr) {
        this.enemy.debug(sr);
        this.renderer.render(world, this.camera.combined);
        sr.setColor(Color.CYAN);
        sr.rect(this.room.x, this.room.y, this.room.width, this.room.height);
    }

    @Override
    public void dispose() {
        this.world.dispose();
        this.renderer.dispose();
    }

    private void createBounds(){
        final BodyDef bd = new BodyDef();
        bd.position.set(room.getCenter(new Vector2()));
        final Body body = world.createBody(bd);
        PolygonShape shape = new PolygonShape();
        {
            FixtureDef fd = new FixtureDef();
            shape.setAsBox(1, 5, new Vector2(21f * .5f,0), 0);
            fd.shape = shape;
            body.createFixture(fd);
        }
        {
            FixtureDef fd = new FixtureDef();
            shape.setAsBox(1, 5, new Vector2(-21f * .5f,0), 0);
            fd.shape = shape;
            body.createFixture(fd);
        }
        {
            FixtureDef fd = new FixtureDef();
            shape.setAsBox(11, 1, new Vector2(0,11f * .5f), 0);
            fd.shape = shape;
            body.createFixture(fd);
        }
        {
            FixtureDef fd = new FixtureDef();
            shape.setAsBox(11, 1, new Vector2(0,-11f * .5f), 0);
            fd.shape = shape;
            body.createFixture(fd);
        }
        shape.dispose();
    }

}
