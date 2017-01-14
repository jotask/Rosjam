package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * WorldManager
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class WorldManager extends GameState{

    private final int velocityIteration = 6;
    private final int positionsIterations = 2;

    private World world;
    private Box2DDebugRenderer renderer;

    public WorldManager(Game game) {
        super(game);

        this.world = new World(new Vector2(0,0), true);
        this.renderer = new Box2DDebugRenderer();

    }

    @Override
    public void update() {
        world.step(Gdx.graphics.getDeltaTime(), velocityIteration, positionsIterations);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        renderer.render(world, game.getCamera().combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
    }

    public World getWorld() { return world; }
}
