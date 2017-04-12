package com.github.jotask.rosjam.option;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.neat.config.Config;
import com.github.jotask.rosjam.neat.config.LoadConfig;
import com.github.jotask.rosjam.util.Ref;

import java.io.IOException;
import java.util.Properties;

/**
 * Options
 *
 * @author Jose Vives Iznardo
 * @since 30/03/2017
 */
public class Options extends CameraState{

    public enum OPTIONS{ NEATFILE, NEATSIMULATION}

    public static final String file = "config/config.properties";

    final Stage stage;

    final SelectBox<String> neatConfig;

    public Options(Camera camera) {
        super(camera);
        FitViewport viewport = new FitViewport(camera.viewportWidth / 2f, camera.viewportHeight / 2f);
        this.stage = new Stage(viewport, Rosjam.get().getSb());
        final Skin skin = Rosjam.get().getAssets().getSkin();
        final Table table = new Table();
        table.align(Align.left);
        {
            {
                Label label = new Label("Config File for NEAT: ", skin);
                table.add(label);

                this.neatConfig = new SelectBox<String>(skin);
                table.add(this.neatConfig).fill();
            }
            table.row().width(viewport.getWorldWidth() - 10f);
            {
                TextButton btn = new TextButton("Delete this config", skin);
                btn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        deleteConfiguration();
                    }
                });
                table.add(btn).colspan(2).padTop(3).fillX();
            }
            table.row();
            {
                TextButton btn = new TextButton("Config Neat Parameters", skin);
                btn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Rosjam.get().getGsm().changeState(GameStateManager.STATE.NEATOPTIONS);
                    }
                });
                table.add(btn).colspan(2).padTop(3).fillX();
            }
            table.row();
            {
                TextButton btn = new TextButton("Test this configuration", skin);
                btn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        save();
                        Rosjam.get().getGsm().changeState(GameStateManager.STATE.NEAT);
                    }
                });
                table.add(btn).colspan(2).padTop(3).fillX();
            }
            table.row();
            {
                final Options instance = this;
                {
                    final TextButton btn = new TextButton("Save", skin);
                    btn.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            instance.save();
                        }
                    });
                    table.add(btn).fill().pad(10f);
                }
                {
                    final TextButton btn = new TextButton("Exit", skin);
                    btn.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            Rosjam.get().getGsm().changeState(GameStateManager.STATE.MENU);
                        }
                    });
                    table.add(btn).fill().pad(10f);
                }
            }
        }
        final ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setFillParent(true);
        this.stage.addActor(scrollPane);

        Gdx.input.setInputProcessor(this.stage);

        this.stage.setDebugAll(Ref.DEBUG);

        this.load();
    }

    private void deleteConfiguration() {
        if(neatConfig.getSelected().equals(OptionsSaveLoad.propertyFile)) {
            return;
        }
        FileHandle file = Gdx.files.local(neatConfig.getSelected());
        file.delete();
        this.load();
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
    public void dispose() { this.stage.dispose(); }

    private void save(){
        Properties props = new Properties();
        FileHandle fileHandle = Gdx.files.local(file);
        if(!fileHandle.exists()){
            try {
                fileHandle.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        props.setProperty(OPTIONS.NEATFILE.name(), this.neatConfig.getSelected());
        props.setProperty(OPTIONS.NEATSIMULATION.name(), this.neatConfig.getSelected());

        try {
            props.store(fileHandle.writer(false), "config for the game");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getNeatConfigFiles(){
        Array<String> files = new Array<String>();

        FileHandle dir = Gdx.files.local(OptionsSaveLoad.PATH + "");

        if(dir.list().length == 0){
            Config dfl = LoadConfig.loadDefault();
            FileHandle fl = Gdx.files.local(OptionsSaveLoad.PATH + "default.properties");
            try {
                dfl.getProperties().store(fl.writer(false), "default neat configuration");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                Gdx.files.local(OptionsSaveLoad.PATH + "");
            }
        }

        for(FileHandle f: dir.list()){
            files.add(f.name());
        }

        neatConfig.setItems(files);

    }

    private void load(){

        this.getNeatConfigFiles();

        FileHandle fileHandle = Gdx.files.local(file);

        if(!fileHandle.exists()){
            try {
                createDefault(fileHandle);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                load();
            }
            return;
        }

        final Properties props = new Properties();

        try {
            props.load(fileHandle.read());

            final String target = props.getProperty(OPTIONS.NEATFILE.name());
            this.neatConfig.setSelected(target);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createDefault(final FileHandle fileHandle) throws IOException {
        Properties properties = new Properties();
        properties.setProperty(OPTIONS.NEATFILE.name(), OptionsSaveLoad.propertyFile);
        properties.setProperty(OPTIONS.NEATSIMULATION.name(), OptionsSaveLoad.propertyFile);
        properties.store(fileHandle.writer(false), "config file for the game");
    }

    @Override
    public void resize(int width, int height) {
        this.camera.resize(width, height);
        this.stage.getViewport().update(width, height);
        this.stage.getViewport().apply();
    }

}
