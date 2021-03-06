package com.github.jotask.rosjam.neat.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.github.jotask.rosjam.option.Options;
import com.github.jotask.rosjam.option.OptionsSaveLoad;

import java.io.IOException;
import java.util.Properties;

/**
 * LoadConfig
 *
 * @author Jose Vives Iznardo
 * @since 22/03/2017
 */
public class LoadConfig {

    public static Config load(boolean isSimulation){

        final Properties globalProperties = new Properties();
        final FileHandle globalOptionFile = Gdx.files.local(Options.FILE);

        if(!globalOptionFile.exists()){
            return LoadConfig.loadDefault();
        }

        try {
            globalProperties.load(globalOptionFile.read());
        } catch (IOException e) {
            e.printStackTrace();
            return LoadConfig.loadDefault();
        }

        final String f;

        if(isSimulation){
            f = globalProperties.getProperty(Options.OPTIONS.NEATSIMULATION.name());
        }else {
            f = globalProperties.getProperty(Options.OPTIONS.NEATFILE.name());
        }

        final String last = globalProperties.getProperty(Options.OPTIONS.LAST_NEAT.name());

        if(last != null && !last.equals(f)){

            FileHandle neat = Gdx.files.local("neat/population.json");

            if(neat.exists())
                neat.delete();


            globalProperties.setProperty(Options.OPTIONS.LAST_NEAT.name(), f);
            try {
                globalProperties.store(globalOptionFile.writer(false), "global properties");
            } catch (IOException e) {
                e.printStackTrace();
                Options.createDefault();
            }
        }

        return loadConfig(f);

    }

    private static Config loadConfig(final String f){

        FileHandle file = Gdx.files.local(OptionsSaveLoad.PATH + f);

        if(!file.exists()){
            return LoadConfig.loadDefault();
        }

        final Properties properties = new Properties();

        try {
            properties.load(file.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Config cfg = new Config(properties);

        if(cfg == null){
            return loadDefault();
        }else{
            return cfg;
        }

    }

    public static Config loadDefault(){

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

        return new Config(properties);

    }

}
