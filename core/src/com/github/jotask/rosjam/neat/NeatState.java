package com.github.jotask.rosjam.neat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.neat.config.Config;
import com.github.jotask.rosjam.neat.config.LoadConfig;
import com.github.jotask.rosjam.neat.gui.EngineGui;
import com.github.jotask.rosjam.neat.gui.JotaGui;
import com.github.jotask.rosjam.neat.gui.NetworkRenderer;
import com.github.jotask.rosjam.util.Ref;

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

    private int last_generation;
    private double last_fitness;

    public NeatState(Camera camera) {
        super(camera);

        ORIGINAL_DEBUG = Ref.DEBUG;
        Ref.DEBUG = true;

        final Config cfg = LoadConfig.load(true);

        this.neat = new Neat(cfg);
        this.engineGui = new EngineGui(this);
        this.renderer = new Box2DDebugRenderer();
        this.jotaGui = new JotaGui(this);
        this.networkRenderer = new NetworkRenderer(this);

        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        this.stage = new Stage(viewport);
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
            final float x = (stage.getWidth() * .5f)  - (btn.getWidth()  * .5f);
            final float y = (stage.getHeight() - btn.getHeight());
            btn.setPosition(x, y);
            this.stage.addActor(btn);
        }
        Gdx.input.setInputProcessor(this.stage);
        this.color.set(Color.WHITE);
    }

    @Override
    public void update() {
        this.stage.act(Gdx.graphics.getDeltaTime());
        final int gen = this.neat.getJota().getPop().getGeneration();
        if(gen > this.last_generation){
            this.jotaGui.getFitness().addFitness(this.last_generation, this.last_fitness);
            this.last_generation = gen;
        }
        this.last_fitness = this.neat.getJota().getBest().getGenome().fitness;
        this.neat.update();
        this.networkRenderer.createNetwork(this.neat.getJota().getBest());
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.renderer.render(neat.getWorld(), camera.combined);
        sb.setProjectionMatrix(camera.combined);
        this.neat.entityManager.render(sb);
        this.neat.getPlayer().render(sb);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        this.jotaGui.debug(sr);
        this.networkRenderer.debug(sr);
        Gdx.gl20.glDisable(GL20.GL_BLEND);
    }

    public EngineGui getEngineGui() { return engineGui; }

    public Neat getNeat() { return neat; }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.getNeat().dispose();
        Ref.DEBUG = ORIGINAL_DEBUG;
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height);
        this.stage.getViewport().apply();
    }

}
