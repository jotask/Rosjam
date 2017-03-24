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
            TextButton start = new TextButton("StartGame", skin);
            start.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Rosjam.get().getGsm().changeState(GameStateManager.STATE.GAME);
                }
            });
            table.add(start).fillX().row();
            TextButton cont = new TextButton("Continue", skin);
            table.add(cont).fillX().row();
            TextButton options = new TextButton("Options", skin);
            table.add(options).fillX().row();
        }
        this.stage.addActor(table);
        this.stage.setDebugAll(true);
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
}
