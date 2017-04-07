package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.GameState;
import com.github.jotask.rosjam.game.hud.dungeon.MapHud;

/**
 * PauseState
 *
 * @author Jose Vives Iznardo
 * @since 29/03/2017
 */
public class PauseState extends GameState {

    final Stage stage;
    final Window pause;

    public final Camera camera;

    final MapHud map;

    public PauseState(final Game game) {
        super(game);
        this.camera = new Camera();
        this.stage = new Stage(game.getHud().getStage().getViewport(), Rosjam.get().getSb());
        final Skin skin = Rosjam.get().getAssets().getSkin();
        this.pause = new Window("Pause", skin);
        {
            final TextButton btn = new TextButton("Continue", skin);
            btn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.changeState(Game.GAMESTATES.PLAY);
                }
            });
            pause.add(btn).fillX().row();
        }
        {
            final TextButton btn = new TextButton("Exit", skin);
            btn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    pause.remove();
                    game.exit(true);
                }
            });
            pause.add(btn).fillX().row();
        }
        float x = camera.position.x + (camera.viewportWidth *.5f) - (pause.getWidth() * .5f);
        float y = camera.position.y + (camera.viewportHeight *.5f) - (pause.getHeight() * .5f);
        this.pause.setPosition(x, y);
        this.pause.pack();
        this.pause.setKeepWithinStage(false);
        this.stage.addActor(pause);

        this.map = new MapHud(this);

    }

    @Override
    public void enterState(){
        this.map.update();
        Gdx.input.setInputProcessor(this.stage);
        float x = camera.position.x + (camera.viewportWidth *.5f) - (pause.getWidth() * .5f);
        float y = camera.position.y + (camera.viewportHeight *.5f) - (pause.getHeight() * .5f);
        this.pause.setPosition(x, y);
        this.pause.pack();
    }

    @Override
    public void update() {
        this.stage.act();
        this.map.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        this.map.render(sb);
        sb.end();
        sb.setProjectionMatrix(camera.combined);
        stage.draw();
        sb.begin();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
