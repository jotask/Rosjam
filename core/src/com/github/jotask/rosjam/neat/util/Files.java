package com.github.jotask.rosjam.neat.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.github.jotask.rosjam.neat.jneat.genetics.Population;
import com.github.jotask.rosjam.neat.jneat.util.Constants;

/**
 * Files
 *
 * @author Jose Vives Iznardo
 * @since 12/03/2017
 */
public abstract class Files {

    static final String PATH = "neat/";

    static final String FILE = PATH + "population";
    static final String EXTENSION = ".json";

    private Files(){}

    public static void save(final Population population){
        final Json json = new Json();
        final String text = json.prettyPrint(population);

        final FileHandle fileHandle = Gdx.files.local(FILE + EXTENSION);
        fileHandle.writeString(text, false);

    }

    public static void delete(){
        FileHandle fileHandle = Gdx.files.local(FILE + EXTENSION);
        if(fileHandle.exists()){
            fileHandle.delete();
        }
    }

    public static Population load(){
        FileHandle fileHandle = Gdx.files.local(FILE + EXTENSION);

        if(!Constants.LOAD){
            fileHandle.delete();
        }

        if(!fileHandle.exists()){
            Population population = new Population();
            population.initialize();
            return population;
        }else{
            return new Json().fromJson(Population.class, fileHandle);
        }
    }

}
