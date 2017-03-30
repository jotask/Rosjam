package com.github.jotask.rosjam.option;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.github.jotask.rosjam.neat.config.Config;
import com.github.jotask.rosjam.neat.config.Default;

import java.io.IOException;
import java.util.Properties;

import static com.github.jotask.rosjam.neat.config.Config.Property.*;

/**
 * OptionsSaveLoad
 *
 * @author Jose Vives Iznardo
 * @since 30/03/2017
 */
public abstract class OptionsSaveLoad {

    public static final String PATH = "config/neat/";

    public static final String propertyFile = "config.properties";

    private OptionsSaveLoad(){}

    static void reloadFiles(final NeatOptions opt, final String dft){

        Array<String> files = new Array<String>();

        FileHandle dir = Gdx.files.local(OptionsSaveLoad.PATH + "");
        for(FileHandle f: dir.list()){
            files.add(f.name());
        }

        opt.select.setItems(files);

        opt.select.setSelected(dft);

    }

    static void load(final NeatOptions opt, final String fileToLoad){

        String f = propertyFile;

        if(fileToLoad != null && opt.select.getSelected() != null){
            f = opt.select.getSelected();
        }

        Properties prop = new Properties();
        FileHandle file = Gdx.files.local(PATH + f);
        if(!file.exists())
            createDefault(file);

        try {
            prop.load(file.read());

            opt.population.setText(prop.getProperty(POPULATION.name()));
            opt.stale_species.setText(prop.getProperty(STALE_SPECIES.name()));
            opt.inittime.setText(prop.getProperty(INIT_TIME.name()));
            opt.penalizationdistance.setText(prop.getProperty(PENALIZATION_DISTANCE.name()));
            opt.penalizationvelocity.setText(prop.getProperty(PENALIZATION_VELOCITY.name()));
            opt.penalizationhits.setText(prop.getProperty(PENALIZATION_HITS.name()));
            opt.deltadisjoint.setText(prop.getProperty(DELTA_DISJOINT.name()));
            opt.deltaweight.setText(prop.getProperty(DELTA_WEIGHTS.name()));
            opt.deltathreshold.setText(prop.getProperty(DELTA_THRESHOLD.name()));
            opt.eachgeneration.setText(prop.getProperty(EACH_GENERATION.name()));
            opt.timeincrease.setText(prop.getProperty(TIME_INCREASE.name()));

            opt.mutation.setValue(new Float(prop.getProperty(MUTATION.name())));
            opt.commmutation.setValue(new Float(prop.getProperty(CONN_MUTATION.name())));
            opt.linkmutation.setValue(new Float(prop.getProperty(LINK_MUTATION.name())));
            opt.biasmutation.setValue(new Float(prop.getProperty(BIAS_MUTATION.name())));
            opt.nodemutation.setValue(new Float(prop.getProperty(NODE_MUTATION.name())));
            opt.enablemutation.setValue(new Float(prop.getProperty(ENABLE_MUTATION.name())));
            opt.disablemutation.setValue(new Float(prop.getProperty(DISABLE_MUTATION.name())));
            opt.stepsize.setValue(new Float(prop.getProperty(STEP_SIZE.name())));
            opt.perturbation.setValue(new Float(prop.getProperty(PERTURBATION.name())));
            opt.crossover.setValue(new Float(prop.getProperty(CROSSOVER.name())));
            opt.thresold.setValue(new Float(prop.getProperty(THRESHOLD.name())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        OptionsSaveLoad.reloadFiles(opt, fileToLoad);

    }

    private static void createDefault(final FileHandle fileHandle){

        final Properties properties = new Properties();
        properties.setProperty(Config.Property.POPULATION.name(), String.valueOf(Default.POPULATION));
        properties.setProperty(Config.Property.STALE_SPECIES.name(), String.valueOf(Default.STALE_SPECIES));
        properties.setProperty(Config.Property.DELTA_DISJOINT.name(), String.valueOf(Default.DELTA_DISJOINT));
        properties.setProperty(Config.Property.DELTA_WEIGHTS.name(), String.valueOf(Default.DELTA_WEIGHTS));
        properties.setProperty(Config.Property.DELTA_THRESHOLD.name(), String.valueOf(Default.DELTA_THRESHOLD));
        properties.setProperty(Config.Property.MUTATION.name(), String.valueOf(Default.MUTATION));
        properties.setProperty(Config.Property.CONN_MUTATION.name(), String.valueOf(Default.CONN_MUTATION));
        properties.setProperty(Config.Property.LINK_MUTATION.name(), String.valueOf(Default.LINK_MUTATION));
        properties.setProperty(Config.Property.BIAS_MUTATION.name(), String.valueOf(Default.BIAS_MUTATION));
        properties.setProperty(Config.Property.NODE_MUTATION.name(), String.valueOf(Default.NODE_MUTATION));
        properties.setProperty(Config.Property.ENABLE_MUTATION.name(), String.valueOf(Default.ENABLE_MUTATION));
        properties.setProperty(Config.Property.DISABLE_MUTATION.name(), String.valueOf(Default.DISABLE_MUTATION));
        properties.setProperty(Config.Property.STEP_SIZE.name(), String.valueOf(Default.STEP_SIZE));
        properties.setProperty(Config.Property.PERTURBATION.name(), String.valueOf(Default.PERTURBATION));
        properties.setProperty(Config.Property.CROSSOVER.name(), String.valueOf(Default.CROSSOVER));
        properties.setProperty(Config.Property.THRESHOLD.name(), String.valueOf(Default.THRESHOLD));
        properties.setProperty(Config.Property.INIT_TIME.name(), String.valueOf(Default.INIT_TIME));
        properties.setProperty(Config.Property.PENALIZATION_DISTANCE.name(), String.valueOf(Default.PENALIZATION_DISTANCE));
        properties.setProperty(Config.Property.PENALIZATION_VELOCITY.name(), String.valueOf(Default.PENALIZATION_VELOCITY));
        properties.setProperty(Config.Property.PENALIZATION_HITS.name(), String.valueOf(Default.PENALIZATION_HITS));
        properties.setProperty(Config.Property.TIME_INCREASE.name(), String.valueOf(Default.TIME_INCREASE));
        properties.setProperty(Config.Property.EACH_GENERATION.name(), String.valueOf(Default.INCREASE_EACH_GENERATION));

        try {
            properties.store(fileHandle.writer(false), "parameters for the neat simulation");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void save(final NeatOptions opt, final String newFile){
        Properties props = new Properties();

        String f = opt.select.getSelected();

        if(newFile != null){
            f = newFile;
        }

        FileHandle file = Gdx.files.local(PATH + f);

        if(!file.exists()){
            try {
                file.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            props.load(file.read());

            // Text fields and check if the inputs is a number
            if(isNumber(opt.population.getText()))
                props.setProperty(POPULATION.name(), String.valueOf(opt.population.getText()));

            if(isNumber(opt.stale_species.getText()))
                props.setProperty(STALE_SPECIES.name(), String.valueOf(opt.stale_species.getText()));

            if(isNumber(opt.inittime.getText()))
                props.setProperty(INIT_TIME.name(), String.valueOf(opt.inittime.getText()));

            if(isNumber(opt.penalizationdistance.getText()))
                props.setProperty(PENALIZATION_DISTANCE.name(), String.valueOf(opt.penalizationdistance.getText()));

            if(isNumber(opt.penalizationvelocity.getText()))
                props.setProperty(PENALIZATION_VELOCITY.name(), String.valueOf(opt.penalizationvelocity.getText()));

            if(isNumber(opt.penalizationhits.getText()))
                props.setProperty(PENALIZATION_HITS.name(), String.valueOf(opt.penalizationhits.getText()));

            if(isNumber(opt.deltadisjoint.getText()))
                props.setProperty(DELTA_DISJOINT.name(), String.valueOf(opt.deltadisjoint.getText()));

            if(isNumber(opt.deltaweight.getText()))
                props.setProperty(DELTA_WEIGHTS.name(), String.valueOf(opt.deltaweight.getText()));

            if(isNumber(opt.deltathreshold.getText()))
                props.setProperty(DELTA_THRESHOLD.name(), String.valueOf(opt.deltathreshold.getText()));

            if(isNumber(opt.eachgeneration.getText()))
                props.setProperty(EACH_GENERATION.name(), String.valueOf(opt.eachgeneration.getText()));

            if(isNumber(opt.timeincrease.getText()))
                props.setProperty(TIME_INCREASE.name(), String.valueOf(opt.timeincrease.getText()));

            // Sliders
            props.setProperty(MUTATION.name(), String.valueOf(opt.mutation.getValue()));
            props.setProperty(CONN_MUTATION.name(), String.valueOf(opt.commmutation.getValue()));
            props.setProperty(LINK_MUTATION.name(), String.valueOf(opt.linkmutation.getValue()));
            props.setProperty(BIAS_MUTATION.name(), String.valueOf(opt.biasmutation.getValue()));
            props.setProperty(NODE_MUTATION.name(), String.valueOf(opt.nodemutation.getValue()));
            props.setProperty(ENABLE_MUTATION.name(), String.valueOf(opt.enablemutation.getValue()));
            props.setProperty(DISABLE_MUTATION.name(), String.valueOf(opt.disablemutation.getValue()));
            props.setProperty(STEP_SIZE.name(), String.valueOf(opt.stepsize.getValue()));
            props.setProperty(PERTURBATION.name(), String.valueOf(opt.perturbation.getValue()));
            props.setProperty(CROSSOVER.name(), String.valueOf(opt.crossover.getValue()));
            props.setProperty(THRESHOLD.name(), String.valueOf(opt.thresold.getValue()));

            props.store(file.writer(false), "property for the game");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isNumber(String text){
        // TODO check if is a number
        return true;
    }

}
