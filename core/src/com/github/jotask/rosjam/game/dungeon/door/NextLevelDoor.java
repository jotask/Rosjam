package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.engine.graphics.DoorSprite;

/**
 * NextLevelDoor
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
public class NextLevelDoor extends Door {

    public NextLevelDoor(Vector2 position, Room room, DoorSprite sprite) {
        super(position,room, sprite);
    }

}
