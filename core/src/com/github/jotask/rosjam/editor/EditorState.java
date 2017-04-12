package com.github.jotask.rosjam.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

/**
 * EditorState
 *
 * @author Jose Vives Iznardo
 * @since 29/01/2017
 */
public class EditorState extends CameraState {

    public enum Tile {
        WALL(Color.PURPLE),
        EMPTY(Color.WHITE),
        ROCK(Color.BROWN),
        SPAWN(Color.RED);

        public final Texture texture;

        Tile(final Color color){
            Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGB565);
            pm.setColor(color);
            pm.fill();
            this.texture = new Texture(pm);
        }

    }

    private final Skin skin;
    private final Stage stage;

    final MenuEditor menuEditor;
    private final EditorScreen editorScreen;

    public EditorState(Camera camera) {
        super(camera);

        this.skin = Rosjam.get().getAssets().getSkin();

        this.menuEditor = new MenuEditor(this);
        this.editorScreen = new EditorScreen(this);

        this.stage = new Stage();
        this.stage.addActor(menuEditor);
        this.stage.addActor(editorScreen);
        this.stage.setDebugAll(false);

        Gdx.input.setInputProcessor(this.stage);

    }

    @Override
    public void update() {
        this.stage.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch sb) {
        this.editorScreen.render(sb);
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }

    public void save(){
        LinkedList<TileData> data = new LinkedList<TileData>();
        for(TileActor a: editorScreen.getTiles()){
            data.add(a.tile);
        }
        Json json = new Json();
        String s = json.prettyPrint(data);
        FileHandle file = Gdx.files.local("rooms/" + randomName());
        file.writeString(s, false);

        editorScreen.reset();

    }

    private String randomName(){ return new BigInteger(130, new Random()).toString(32); }

    public void load(final String f){
        FileHandle file = Gdx.files.internal("rooms/" + f);
        Json json = new Json();

        final LinkedList<TileData> tiles = json.fromJson(LinkedList.class, TileData.class, file);
        final LinkedList<TileActor> ttt = editorScreen.getTiles();

        for(int i = 0; i < tiles.size(); i++){
            TileActor ta = ttt.get(i);
            ta.tile = tiles.get(i);
        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
        stage.getViewport().apply();
    }

    public Skin getSkin() { return skin; }

    public Stage getStage() { return stage; }

}

