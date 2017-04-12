package com.github.jotask.rosjam.option;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.neat.jneat.util.JException;

/**
 * SaveDialog
 *
 * @author Jose Vives Iznardo
 * @since 30/03/2017
 */
class SaveDialog extends Dialog {

    enum OPTION { EXIT, OVERRIDE, NEW }

    final NeatOptions options;

    SaveDialog(final NeatOptions opt) {
        super("Save", Rosjam.get().getAssets().getSkin());
        this.options = opt;

        this.text("Override or create a new config file");
        this.row();

        if(!opt.select.getSelected().equals(OptionsSaveLoad.propertyFile)) {
            this.button("Override", OPTION.OVERRIDE);
        }
        this.button("New", OPTION.NEW);
        this.button("Exit", OPTION.EXIT);

        this.show(opt.stage);
    }

    @Override
    protected void result(Object object) {

        if(!(object instanceof OPTION))
            throw new RuntimeException("Error");

        final OPTION opt = (OPTION) object;
        switch (opt){
            case EXIT:
                this.hide();
                break;
            case NEW:
                this.hide();
                new NewFile(this.options);
                break;
            case OVERRIDE:
                OptionsSaveLoad.save(this.options, null);
                break;
            default:
                throw new JException("Exception in option");
        }
    }

    private class NewFile extends Dialog{

        final NeatOptions opt;

        final TextField file;

        public NewFile(final NeatOptions opt) {
            super("", Rosjam.get().getAssets().getSkin());
            this.opt = opt;

            this.text("Enter the name for the new file:");
            file = new TextField("", Rosjam.get().getAssets().getSkin());
            this.add(file);
            this.row();

            this.button("Save", true);
            this.button("Cancel", false);

            this.show(this.opt.stage);

        }

        @Override
        protected void result(Object object) {
            final Boolean b = (Boolean) object;
            if(b){
                final String f = file.getText() + ".properties";
                OptionsSaveLoad.save(opt, f);
                OptionsSaveLoad.reloadFiles(opt, f);
            }else{
                this.hide();
            }
        }
    }

}
