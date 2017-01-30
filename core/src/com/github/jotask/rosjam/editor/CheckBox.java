package com.github.jotask.rosjam.editor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.LinkedList;

/**
 * CheckBox
 *
 * @author Jose Vives Iznardo
 * @since 30/01/2017
 */
public class CheckBox extends Table {

    public final MenuEditor menuEditor;

    private LinkedList<com.badlogic.gdx.scenes.scene2d.ui.CheckBox> checkBoxes;

    private com.badlogic.gdx.scenes.scene2d.ui.CheckBox selected;

    public CheckBox(MenuEditor menuEditor) {
        this.menuEditor = menuEditor;

        Skin skin = menuEditor.editorState.getSkin();

        this.checkBoxes = new LinkedList<com.badlogic.gdx.scenes.scene2d.ui.CheckBox>();

        for(int i = 1; i < EditorState.Tile.values().length; i++){
            String s = EditorState.Tile.values()[i].name();
            final com.badlogic.gdx.scenes.scene2d.ui.CheckBox c = new com.badlogic.gdx.scenes.scene2d.ui.CheckBox(s, skin);
            c.setName(s);
            c.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    change(c);
                }
            });
            this.checkBoxes.add(c);
            this.add(c).row();
        }

        this.selected = checkBoxes.getFirst();
        this.selected.setChecked(true);

    }

    private void change(com.badlogic.gdx.scenes.scene2d.ui.CheckBox c){
        this.selected.setChecked(false);
        this.selected = c;
        this.selected.setChecked(true);
    }

    public com.badlogic.gdx.scenes.scene2d.ui.CheckBox getSelected() { return selected; }

}
