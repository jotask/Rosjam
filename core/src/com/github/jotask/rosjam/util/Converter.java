package com.github.jotask.rosjam.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

/**
 * Converter
 *
 * @author Jose Vives Iznardo
 * @since 28/01/2017
 */
public class Converter {

    public Converter(){
        this("sprites.json");
    }

    public Converter(final String input_file){
        Json json = new Json();
        ArrayList<Tile> tiles = json.fromJson(ArrayList.class, Tile.class, Gdx.files.internal("convert/" + input_file));

        StringBuilder builder = new StringBuilder();
        newLine(builder, replace(input_file, ".json", ".png"));
        newLine(builder, "format: RGBA8888");
        newLine(builder, "filter: Nearest, Nearest");
        newLine(builder, "repeat: none");
        for(Tile t: tiles){
            builder.append(t.toString());
        }

        String output_file = replace(input_file, ".json", ".atlas");

        FileHandle file = Gdx.files.local("convert/" + output_file);
        file.writeString(builder.toString(), false);

    }

    private String replace(String file, String replace, String replace_with){
        String output_file = new String(file);
        output_file = output_file.replace(replace, replace_with);
        return output_file;
    }

    static class Tile{
        String name;
        int x, y, width, height;

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            newLine(b, name);
            newLine(b, "    rotate: false");
            newLine(b, "    xy: " + x + ", " + y);
            newLine(b, "    size: " + width + ", " + height);
            newLine(b, "    orig: " + width + ", " + height);
            newLine(b, "    offset: 0, 0");
            newLine(b, "    index: -1");
            return b.toString();
        }

    }

    private static void newLine(final StringBuilder sb, String text){
        sb.append(text);
        sb.append(System.getProperty("line.separator"));
    }

}
