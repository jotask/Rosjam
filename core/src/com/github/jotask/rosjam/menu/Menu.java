package com.github.jotask.rosjam.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.util.Ref;

/**
 * Menu
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public class Menu extends CameraState {

    private final Logo logo;

    private Stage stage;

    public Menu(Camera camera) {
        super(camera);
        this.logo = new Logo(camera);
        this.stage = new Stage(this.camera.viewport, Rosjam.get().getSb());
        final Skin skin = Rosjam.get().getAssets().getSkin();
        Table table = new Table();
        table.setFillParent(true);
        table.padTop(125f);
        {
            TextButton start = new TextButton("Start new run", skin);
            start.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Rosjam.get().getGsm().changeState(GameStateManager.STATE.GAME);
                }
            });
            table.add(start).fillX().padBottom(5f).row();

            // TODO implement continue
            TextButton cont = new TextButton("Continue run", skin);
            table.add(cont).fillX().padBottom(5f).row();

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


        }
        this.stage.addActor(table);
        this.stage.setDebugAll(Ref.DEBUG);
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void update() {
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.logo.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.end();
        this.stage.draw();
        sb.begin();
        this.logo.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        this.logo.debug(sr);
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height);
    }
}
