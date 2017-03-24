package com.github.jotask.rosjam.option;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.util.Ref;

/**
 * Options
 *
 * @author Jose Vives Iznardo
 * @since 24/03/2017
 */
public class Options extends CameraState {

    private final Stage stage;

    public Options(Camera camera) {
        super(camera);
        this.stage = new Stage(camera.viewport, Rosjam.get().getSb());
        final Skin skin = Rosjam.get().getAssets().getSkin();
        Table table = new Table();
        table.setFillParent(true);
        {
            {
                Label label = new Label("Population: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(400f);

            }
            table.row();
            {
                Label label = new Label("Crossover: ", skin);
                table.add(label);

                Slider field = new Slider(0,1f, 0.001f, false, skin);
                table.add(field).width(400f);
            }
        }
        this.stage.addActor(table);
        this.stage.setDebugAll(Ref.DEBUG);
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void update() { this.stage.act(Gdx.graphics.getDeltaTime()); }

    @Override
    public void render(SpriteBatch sb) {
        sb.end();
        this.stage.draw();
        sb.begin();
    }
}
