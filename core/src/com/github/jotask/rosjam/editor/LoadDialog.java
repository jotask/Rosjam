package com.github.jotask.rosjam.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.utils.Array;

/**
 * LoadDialog
 *
 * @author Jose Vives Iznardo
 * @since 30/01/2017
 */
public class LoadDialog extends Dialog {

    private final EditorState editorState;

    private SelectBox<String> selectBox;

    public LoadDialog(EditorState editorState) {
        this("LoadDialog", editorState);
    }

    private LoadDialog(String title, EditorState editorState) {
        super(title, editorState.getSkin());

        this.editorState = editorState;

        this.text("Are you sure you want to quit?");

        Array<String> files = new Array<String>();

        FileHandle dir = Gdx.files.internal("rooms");
        for(FileHandle f: dir.list()){
            files.add(f.name());
        }

        selectBox = new SelectBox<String>(editorState.getSkin());
        selectBox.setItems(files);

        this.add(selectBox);

        this.row();

        this.button("Load", true); //sends "true" as the result
        this.button("Close", false);  //sends "false" as the result

        this.show(editorState.getStage());

    }

    @Override
    protected void result(Object object) {
        Boolean b = (Boolean) object;
        if(b)
            editorState.load(selectBox.getSelected());
    }

}

