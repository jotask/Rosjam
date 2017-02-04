package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * NextLevelDoor
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
public class NextLevelDoor extends Door {

    public NextLevelDoor(Vector2 position, Room room, Animation<TextureRegion> animation) {
        super(position,room, animation);
    }

}
