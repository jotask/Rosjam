package com.github.jotask.rosjam.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.game.InitialParameters;
import com.github.jotask.rosjam.util.Ref;

/**
 * Menu
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class Menu extends CameraState {

    private final Stage stage;
    private final ParticleEffect particleEffect;

    public Menu(Camera camera) {
        super(camera);
        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        this.stage = new Stage(viewport, Rosjam.get().getSb());
        {
            Logo logo = new Logo();
            logo.setPosition(this.stage.getWidth() *.5f - (logo.getWidth() * .5f), this.stage.getHeight() * .5f + 75f);
            logo.setOrigin(logo.getWidth() *.5f, logo.getHeight() * .5f);
            this.stage.addActor(logo);
        }
        final Skin skin = Rosjam.get().getAssets().getSkin();
        Table table = new Table();
        table.setFillParent(true);
        table.padTop(125f);
        {
            {

                final FileHandle file = Gdx.files.local(InitialParameters.file);

                TextButton start = new TextButton("Start new run", skin);
                start.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        file.delete();
                        Rosjam.get().getGsm().changeState(GameStateManager.STATE.GAME);
                    }
                });
                table.add(start).fillX().padBottom(5f).row();

                if (file.exists()) {
                    TextButton cont = new TextButton("Continue run", skin);
                    cont.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            Rosjam.get().getGsm().changeState(GameStateManager.STATE.GAME);
                        }
                    });
                    table.add(cont).fillX().padBottom(5f).row();
                }
            }

            TextButton options = new TextButton("Options", skin);
            options.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Rosjam.get().getGsm().changeState(GameStateManager.STATE.OPTIONS);
                }
            });
            table.add(options).fillX().padBottom(5f).row();

            TextButton simulation = new TextButton("Simulation", skin);
            simulation.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Rosjam.get().getGsm().changeState(GameStateManager.STATE.NEAT);
                }
            });
            table.add(simulation).fillX().padBottom(5f).row();

            TextButton exit = new TextButton("Exit", skin);
            exit.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });
            table.add(exit).fillX().padBottom(5f).row();


        }
        this.stage.addActor(table);
        this.stage.setDebugAll(Ref.DEBUG);
        Gdx.input.setInputProcessor(this.stage);

        this.particleEffect = new ParticleEffect();
        this.particleEffect.load(Gdx.files.internal("particles/menu.p"),Gdx.files.internal("particles/"));
        this.particleEffect.getEmitters().first().setPosition(0,0);
        this.particleEffect.getEmitters().first().getSpawnWidth() .setHigh(0, camera.viewportWidth);
        this.particleEffect.getEmitters().first().getSpawnHeight().setHigh(0, camera.viewportHeight);
        this.particleEffect.start();

        this.color.set(Color.valueOf("#728ab2"));

    }

    @Override
    public void update() {
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.particleEffect.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch sb) {
        this.particleEffect.draw(sb);
        sb.end();
        this.stage.draw();
        sb.begin();
        if(this.particleEffect.isComplete()){
            this.particleEffect.reset();
        }
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.particleEffect.dispose();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height);
    }

}
