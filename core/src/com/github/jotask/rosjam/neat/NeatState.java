package com.github.jotask.rosjam.neat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.neat.gui.EngineGui;
import com.github.jotask.rosjam.neat.gui.JotaGui;
import com.github.jotask.rosjam.neat.gui.NetworkRenderer;
import com.github.jotask.rosjam.util.Ref;

import static com.badlogic.gdx.Gdx.gl;

/**
 * NeatState
 *
 * @author Jose Vives Iznardo
 * @since 28/03/2017
 */
public class NeatState extends CameraState {

    private final boolean ORIGINAL_DEBUG;

    private final Neat neat;
    private final Box2DDebugRenderer renderer;
    private final EngineGui engineGui;
    private final JotaGui jotaGui;
    private final NetworkRenderer networkRenderer;

    private final Stage stage;

    public NeatState(Camera camera) {
        super(camera);
        ORIGINAL_DEBUG = Ref.DEBUG;
        Ref.DEBUG = true;
        this.neat = new Neat();
        this.engineGui = new EngineGui(this);
        this.renderer = new Box2DDebugRenderer();
        this.jotaGui = new JotaGui(this);
        this.networkRenderer = new NetworkRenderer(this);

        this.stage = new Stage();
        {
            final Skin skin = Rosjam.get().getAssets().getSkin();
            TextButton btn = new TextButton("Back to Menu", skin);
            {
                btn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Rosjam.get().getGsm().changeState(GameStateManager.STATE.MENU);
                    }
                });
            }
            this.stage.addActor(btn);
        }
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void update() {
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.neat.update();
        this.networkRenderer.createNetwork(this.neat.getJota().getBest());
        // TODO update fitness gui
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
        sb.end();
        this.stage.draw();
        sb.begin();
        sb.setProjectionMatrix(this.engineGui.getCamera().combined);
        this.jotaGui.render(sb);
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

    @Override
    public void dispose() {
        this.stage.dispose();
        this.getNeat().dispose();
        Ref.DEBUG = ORIGINAL_DEBUG;
    }
}
