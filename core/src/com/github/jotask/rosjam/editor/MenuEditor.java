package com.github.jotask.rosjam.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * MenuEditor
 *
 * @author Jose Vives Iznardo
 * @since 29/01/2017
 */
public class MenuEditor extends Table {

    public static final float WIDTH = 100f;

    public final EditorState editorState;

    private TextButton save;
    private TextButton load;

    public final CheckBox checkBox;

    public MenuEditor(final EditorState editorState) {
        this.editorState = editorState;

        this.setSize(WIDTH, Gdx.graphics.getHeight());
        this.top();

        Label label = new Label("Room Editor.", editorState.getSkin());
        this.add(label).padBottom(20f);
        this.row();

        this.checkBox = new CheckBox(this);

        this.add(checkBox);

        this.row();

        this.checkBox.padBottom(20f);

        this.save = new TextButton("Save", editorState.getSkin());
        this.save.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                editorState.save();
            }
        });
        this.add(save);
        this.row();

        this.load = new TextButton("Load", editorState.getSkin());
        this.load.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                new LoadDialog(editorState);
            }
        });
        this.add(load);
        this.row();

        this.align(Align.center);

    }



}
