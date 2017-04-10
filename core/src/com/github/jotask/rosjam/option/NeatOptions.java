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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.util.Ref;
import com.github.jotask.rosjam.util.Util;

/**
 * NeatOptions
 *
 * @author Jose Vives Iznardo
 * @since 24/03/2017
 */
public class NeatOptions extends CameraState {

    final Stage stage;

    final TextField population;
    final TextField stale_species;
    final Slider mutation;
    final Slider commmutation;
    final Slider linkmutation;
    final Slider biasmutation;
    final Slider nodemutation;
    final Slider enablemutation;
    final Slider disablemutation;
    final Slider stepsize;
    final Slider perturbation;
    final Slider crossover;
    final Slider thresold;
    final TextField inittime;
    final TextField penalizationdistance;
    final TextField penalizationvelocity;
    final TextField penalizationhits;
    final TextField deltadisjoint;
    final TextField deltaweight;
    final TextField deltathreshold;

    final TextField timeincrease;
    final TextField eachgeneration;

    final SelectBox<String> select;

    // FIXME when is not files create a default file

    public NeatOptions(Camera camera) {
        super(camera);
        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        this.stage = new Stage(viewport, Rosjam.get().getSb());
        final float width = viewport.getScreenWidth() / 4f;
        final Skin skin = Rosjam.get().getAssets().getSkin();
        {
            Table table = new Table();
            table.align(Align.left);
            {
            {
                final float pad = 23f;
                Label label = new Label("Configuration for the Neat Parameters", skin);
                table.add(label);
            }
            table.row();
                {
                    select = new SelectBox<String>(skin);
                    table.add(select).colspan(2).width(width);
                }
                table.row();
                {
                    Label label = new Label("Population: ", skin);
                    table.add(label);

                    this.population = new TextField("", skin);
                    table.add(this.population).width(width);

                }
                table.row();
                {
                    Label label = new Label("Stale Species: ", skin);
                    table.add(label);

                    this.stale_species = new TextField("", skin);
                    table.add(this.stale_species).width(width);
                }
                table.row();
                {
                    Label label = new Label("Mutation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    this.mutation = new Slider(0, 1f, 0.001f, false, skin);
                    this.mutation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(mutation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(this.mutation).width(width);
                    table.add(value);

                }
                table.row();
                {
                    Label label = new Label("Comm mutation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    commmutation = new Slider(0, 1f, 0.001f, false, skin);
                    commmutation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(commmutation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(commmutation).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Link mutation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    linkmutation = new Slider(0, 1f, 0.001f, false, skin);
                    linkmutation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(linkmutation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(linkmutation).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Bias mutation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    biasmutation = new Slider(0, 1f, 0.001f, false, skin);
                    biasmutation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(biasmutation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(biasmutation).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Node mutation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    nodemutation = new Slider(0, 1f, 0.001f, false, skin);
                    nodemutation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(nodemutation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(nodemutation).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Enable mutation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    enablemutation = new Slider(0, 1f, 0.001f, false, skin);
                    enablemutation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(enablemutation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(enablemutation).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Disable Mutation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    disablemutation = new Slider(0, 1f, 0.001f, false, skin);
                    disablemutation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(disablemutation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(disablemutation).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Step Size: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    stepsize = new Slider(0, 1f, 0.001f, false, skin);
                    stepsize.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(stepsize.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(stepsize).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Perturbation: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    perturbation = new Slider(0, 1f, 0.001f, false, skin);
                    perturbation.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(perturbation.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(perturbation).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Crossover: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    crossover = new Slider(0, 1f, 0.001f, false, skin);
                    crossover.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(crossover.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(crossover).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Threshold: ", skin);
                    table.add(label);

                    final Label value = new Label("0.0", skin);

                    thresold = new Slider(0, 1f, 0.001f, false, skin);
                    thresold.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            float v = Util.cutDecimals(thresold.getValue(), 3);
                            value.setText(String.valueOf(v));
                        }
                    });
                    table.add(thresold).width(width);
                    table.add(value);
                }
                table.row();
                {
                    Label label = new Label("Init time: ", skin);
                    table.add(label);

                    inittime = new TextField("", skin);
                    table.add(inittime).width(width);
                }
                table.row();
                {
                    Label label = new Label("Penalization distance: ", skin);
                    table.add(label);

                    penalizationdistance = new TextField("", skin);
                    table.add(penalizationdistance).width(width);
                }
                table.row();
                {
                    Label label = new Label("Penalization velocity: ", skin);
                    table.add(label);

                    penalizationvelocity = new TextField("", skin);
                    table.add(penalizationvelocity).width(width);
                }
                table.row();
                {
                    Label label = new Label("Penalization hits: ", skin);
                    table.add(label);

                    penalizationhits = new TextField("", skin);
                    table.add(penalizationhits).width(width);
                }
                table.row();
                {
                    Label label = new Label("Delta Disjoint: ", skin);
                    table.add(label);

                    deltadisjoint = new TextField("", skin);
                    table.add(deltadisjoint).width(width);
                }
                table.row();
                {
                    Label label = new Label("Delta weight: ", skin);
                    table.add(label);

                    deltaweight = new TextField("", skin);
                    table.add(deltaweight).width(width);
                }
                table.row();
                {
                    Label label = new Label("Delta Threshold: ", skin);
                    table.add(label);

                    deltathreshold = new TextField("", skin);
                    table.add(deltathreshold).width(width);
                }
                table.row();
                {
                    Label label = new Label("Time increase: ", skin);
                    table.add(label);

                    timeincrease = new TextField("", skin);
                    table.add(timeincrease).width(width);
                }
                table.row();
                {
                    Label label = new Label("Increase in each generations: ", skin);
                    table.add(label);

                    eachgeneration = new TextField("", skin);
                    table.add(eachgeneration).width(width);
                }
                table.row();
                {
                    final NeatOptions instance = this;
                    {
                        final TextButton btn = new TextButton("Save", skin);
                        btn.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                new SaveDialog(instance);
                            }
                        });
                        table.add(btn).align(Align.center).padTop(20f).padBottom(50f);
                    }
                    {
                        final TextButton btn = new TextButton("Load", skin);
                        btn.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                OptionsSaveLoad.load(instance, select.getSelected());
                            }
                        });
                        table.add(btn).align(Align.center);
                    }
                    {
                        final TextButton btn = new TextButton("Exit", skin);
                        btn.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                Rosjam.get().getGsm().changeState(GameStateManager.STATE.OPTIONS);
                            }
                        });
                        table.add(btn);
                    }
                }
                final ScrollPane scroll = new ScrollPane(table, skin);
                scroll.setFillParent(true);
                this.stage.addActor(scroll);
            }
            OptionsSaveLoad.reloadFiles(this, null);
            OptionsSaveLoad.load(this, OptionsSaveLoad.propertyFile);
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

    @Override
    public void dispose() {
        super.dispose();
        this.stage.dispose();
    }
}
