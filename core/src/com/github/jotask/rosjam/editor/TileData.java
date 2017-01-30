package com.github.jotask.rosjam.editor;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * TileData
 *
 * @author Jose Vives Iznardo
 * @since 30/01/2017
 */
public class TileData implements Json.Serializable{

    public int x;
    public int y;
    public EditorState.Tile tile;

    @Override
    public void write(Json json) {
        json.writeValue("x", x);
        json.writeValue("y", y);
        json.writeValue("tile", tile);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        JsonValue xx = jsonData.get("x");
        JsonValue yy = jsonData.get("y");
        JsonValue tiletile = jsonData.get("tile");

        this.x = xx.asInt();
        this.y = yy.asInt();

        String v = tiletile.asString();
        for(EditorState.Tile t: EditorState.Tile.values()){
            if(t.name().equals(v)){
                this.tile = t;
                break;
            }
        }

    }

}
