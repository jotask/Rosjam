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

    public NeatOptions(Camera camera) {
        super(camera);
        FitViewport viewport = new FitViewport(camera.viewportWidth / 2f, camera.viewportHeight / 2f);
        this.stage = new Stage(viewport, Rosjam.get().getSb());
        final Skin skin = Rosjam.get().getAssets().getSkin();
        Table table = new Table();
        table.align(Align.center);
        final float pad = 5f;
        {
            Label label = new Label("Configuration for the Neat Parameters", skin);
            table.add(label).colspan(3).expandX().padTop(pad);
        }
        table.row().padBottom(pad);
        {
            select = new SelectBox<String>(skin);
            table.add(select).colspan(3).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Population: ", skin);
            table.add(label).align(Align.right);

            this.population = new TextField("", skin);
            table.add(this.population).colspan(2).fillX();

        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Stale Species: ", skin);
            table.add(label).align(Align.right);

            this.stale_species = new TextField("", skin);
            table.add(this.stale_species).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Mutation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            this.mutation = new Slider(0, 1f, 0.001f, false, skin);
            this.mutation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(mutation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(this.mutation).expandX().fillX();
            table.add(value);

        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Comm mutation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            commmutation = new Slider(0, 1f, 0.001f, false, skin);
            commmutation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(commmutation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(commmutation).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Link mutation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            linkmutation = new Slider(0, 1f, 0.001f, false, skin);
            linkmutation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(linkmutation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(linkmutation).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Bias mutation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            biasmutation = new Slider(0, 1f, 0.001f, false, skin);
            biasmutation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(biasmutation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(biasmutation).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Node mutation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            nodemutation = new Slider(0, 1f, 0.001f, false, skin);
            nodemutation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(nodemutation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(nodemutation).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Enable mutation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            enablemutation = new Slider(0, 1f, 0.001f, false, skin);
            enablemutation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(enablemutation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(enablemutation).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Disable Mutation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            disablemutation = new Slider(0, 1f, 0.001f, false, skin);
            disablemutation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(disablemutation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(disablemutation).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Step Size: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            stepsize = new Slider(0, 1f, 0.001f, false, skin);
            stepsize.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(stepsize.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(stepsize).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Perturbation: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            perturbation = new Slider(0, 1f, 0.001f, false, skin);
            perturbation.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(perturbation.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(perturbation).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Crossover: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            crossover = new Slider(0, 1f, 0.001f, false, skin);
            crossover.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(crossover.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(crossover).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Threshold: ", skin);
            table.add(label).align(Align.right);

            final Label value = new Label("0.0", skin);

            thresold = new Slider(-1f, 1f, 0.001f, false, skin);
            thresold.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float v = Util.cutDecimals(thresold.getValue(), 3);
                    value.setText(String.valueOf(v));
                }
            });
            table.add(thresold).expandX().fillX();
            table.add(value).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Init time: ", skin);
            table.add(label).align(Align.right);

            inittime = new TextField("", skin);
            table.add(inittime).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Penalization distance: ", skin);
            table.add(label).align(Align.right);

            penalizationdistance = new TextField("", skin);
            table.add(penalizationdistance).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Penalization velocity: ", skin);
            table.add(label).align(Align.right);

            penalizationvelocity = new TextField("", skin);
            table.add(penalizationvelocity).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Penalization hits: ", skin);
            table.add(label).align(Align.right);

            penalizationhits = new TextField("", skin);
            table.add(penalizationhits).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Delta Disjoint: ", skin);
            table.add(label).align(Align.right);

            deltadisjoint = new TextField("", skin);
            table.add(deltadisjoint).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Delta weight: ", skin);
            table.add(label).align(Align.right);

            deltaweight = new TextField("", skin);
            table.add(deltaweight).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Delta Threshold: ", skin);
            table.add(label).align(Align.right);

            deltathreshold = new TextField("", skin);
            table.add(deltathreshold).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Time increase: ", skin);
            table.add(label).align(Align.right);

            timeincrease = new TextField("", skin);
            table.add(timeincrease).colspan(2).fillX();
        }
        table.row().padBottom(pad);
        {
            Label label = new Label("Increase in generations: ", skin);
            table.add(label).align(Align.right);

            eachgeneration = new TextField("", skin);
            table.add(eachgeneration).colspan(2).fillX();
        }
        table.row().padBottom(pad);
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
                table.add(btn).pad(10f).fill();
            }
            {
                final TextButton btn = new TextButton("Load", skin);
                btn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        OptionsSaveLoad.load(instance, select.getSelected());
                    }
                });
                table.add(btn).pad(10f).fill();
            }
            table.row();
            {
                final TextButton btn = new TextButton("Exit", skin);
                btn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Rosjam.get().getGsm().changeState(GameStateManager.STATE.OPTIONS);
                    }
                });
                table.add(btn).pad(10f).colspan(3).fill();
            }
        }
        final ScrollPane scroll = new ScrollPane(table, skin);
        scroll.setFillParent(true);
        this.stage.addActor(scroll);

        this.stage.setDebugAll(Ref.DEBUG);

        Gdx.input.setInputProcessor(this.stage);

        OptionsSaveLoad.reloadFiles(this, null);
        OptionsSaveLoad.load(this, OptionsSaveLoad.propertyFile);
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
    public void resize(int width, int height) {
        this.camera.resize(width, height);
        this.stage.getViewport().update(width, height);
        this.stage.getViewport().apply();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.stage.dispose();
    }

}
