package com.github.jotask.rosjam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.GameState;

/**
 * GameOverState
 *
 * @author Jose Vives Iznardo
 * @since 29/03/2017
 */
public class GameOverState extends GameState {

    final Stage stage;
    final Window pause;

    final Camera camera;

    final Label score;

    public GameOverState(final Game game) {
        super(game);
        this.camera = new Camera();
        this.stage = new Stage(game.getHud().getStage().getViewport(), Rosjam.get().getSb());
        final Skin skin = Rosjam.get().getAssets().getSkin();
        this.pause = new Window("GameOver", skin);
        {
            final Label lab = new Label("Max score: ", skin);
            this.pause.add(lab).row();

            score = new Label(String.valueOf(game.getPlay().score.getScore()), skin);
            this.pause.add(score).row();
        }
        {
            final TextButton btn = new TextButton("Exit", skin);
            btn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    pause.remove();
                    game.exit(false);
                }
            });
            this.pause.add(btn).row();
        }
        final float x = (this.stage.getWidth()  * .5f) - (this.pause.getWidth()  * .5f);
        final float y = (this.stage.getHeight() * .5f) - (this.pause.getHeight() * .5f);
        this.pause.setPosition(x, y);
        this.pause.pack();
        this.pause.setKeepWithinStage(false);
        this.stage.addActor(this.pause);
    }

    @Override
    public void enterState(){
        Gdx.input.setInputProcessor(this.stage);
        this.score.setText(String.valueOf(this.game.getPlay().score.getScore()));
        final float x = (this.stage.getWidth()  * .5f) - (this.pause.getWidth()  * .5f);
        final float y = (this.stage.getHeight() * .5f) - (this.pause.getHeight() * .5f);
        this.pause.setPosition(x, y);
        this.pause.pack();
    }

    @Override
    public void update() {
        this.stage.act();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.end();
        this.stage.draw();
        sb.begin();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }


}
