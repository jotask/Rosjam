package com.github.jotask.rosjam.option;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.util.Ref;
import com.github.jotask.rosjam.util.Util;

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
        final float width = 300f;
        this.stage = new Stage(camera.viewport, Rosjam.get().getSb());
        final Skin skin = Rosjam.get().getAssets().getSkin();
        Table table = new Table();
        table.align(Align.left);
        {
//            {
//                final float pad = 23f;
//                Label label = new Label("Configuration for the Neat Parameters", skin);
//                table.add(label);
//            }
//            table.row();
            {
                Label label = new Label("Population: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);

            }
            table.row();
            {
                Label label = new Label("Stale Species: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                Label label = new Label("Mutation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);

            }
            table.row();
            {
                Label label = new Label("Comm mutation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Link mutation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Bias mutation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Node mutation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Enable mutation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Disable Mutation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Step Size: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Perturbation: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Crossover: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Threshold: ", skin);
                table.add(label);

                final Label value = new Label("0.0", skin);

                final Slider field = new Slider(0,1f, 0.001f, false, skin);
                field.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        float v = Util.cutDecimals(field.getValue(), 3);
                        value.setText(String.valueOf(v));
                    }
                });
                table.add(field).width(width);
                table.add(value);
            }
            table.row();
            {
                Label label = new Label("Init time: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                Label label = new Label("Penalization distance: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                Label label = new Label("Penalization velocity: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                Label label = new Label("Penalization hits: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                Label label = new Label("Delta Disjoint: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                Label label = new Label("Delta weight: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                Label label = new Label("Delta Threshold: ", skin);
                table.add(label);

                TextField field = new TextField("", skin);
                table.add(field).width(width);
            }
            table.row();
            {
                {
                    final TextButton btn = new TextButton("Save", skin);
                    table.add(btn).align(Align.center).padTop(20f).padBottom(50f);
                }
                {
                    final TextButton btn = new TextButton("Load", skin);
                    table.add(btn).align(Align.center);
                }
                {
                    final TextButton btn = new TextButton("Exit", skin);
                    btn.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            Rosjam.get().getGsm().changeState(GameStateManager.STATE.MENU);
                        }
                    });
                    table.add(btn);
                }
            }
            final ScrollPane scroll = new ScrollPane(table, skin);
            scroll.setFillParent(true);
            this.stage.addActor(scroll);
        }
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
