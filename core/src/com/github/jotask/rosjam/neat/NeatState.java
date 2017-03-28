package com.github.jotask.rosjam.neat;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.neat.gui.EngineGui;
import com.github.jotask.rosjam.neat.gui.JotaGui;
import com.github.jotask.rosjam.neat.gui.NetworkRenderer;

import static com.badlogic.gdx.Gdx.gl;

/**
 * NeatState
 *
 * @author Jose Vives Iznardo
 * @since 28/03/2017
 */
public class NeatState extends CameraState {

    private final Neat neat;

    private final Box2DDebugRenderer renderer;

    private final EngineGui engineGui;

    private final JotaGui jotaGui;
    private final NetworkRenderer networkRenderer;

    public NeatState(Camera camera) {
        super(camera);

        this.neat = new Neat();

        this.engineGui = new EngineGui(this);

        this.renderer = new Box2DDebugRenderer();


        this.jotaGui = new JotaGui(this);
        this.networkRenderer = new NetworkRenderer(this);
    }

    @Override
    public void update() {
        this.neat.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        gl.glClearColor(1f, 1f, 1f, 1f);

        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.renderer.render(neat.getWorld(), camera.combined);

        sb.setProjectionMatrix(camera.combined);

        neat.entityManager.render(sb);
        neat.getPlayer().render(sb);

        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

    }

    @Override
    public void postRender(SpriteBatch sb) {
        sb.setProjectionMatrix(this.engineGui.getCamera().combined);
        this.jotaGui.render(sb);
        this.networkRenderer.render(sb);
        this.engineGui.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {

        sr.setProjectionMatrix(camera.combined);

        sr.set(ShapeRenderer.ShapeType.Filled);
        this.neat.entityManager.debug(sr);
        this.neat.getPlayer().debug(sr);
    }

    @Override
    public void postDebug(ShapeRenderer sr) {
        sr.setProjectionMatrix(this.engineGui.getCamera().combined);


        gl.glEnable(GL20.GL_BLEND);
        this.jotaGui.debug(sr);
        this.networkRenderer.debug(sr);
        gl.glDisable(GL20.GL_BLEND);

    }

    public EngineGui getEngineGui() { return engineGui; }

    public Neat getNeat() { return neat; }

}
