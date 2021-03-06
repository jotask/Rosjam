package com.github.jotask.rosjam.game.dungeon.door;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.engine.graphics.DoorSprite;

/**
 * RoomDoor
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
public class RoomDoor extends Door {

    public Door.SIDE side;

    public RoomDoor(Vector2 position, SIDE side, Room room, DoorSprite sprite) {
        super(position, room, sprite);
        this.side = side;
    }

    public Door.SIDE getOpposite(){
        Door.SIDE side = this.side;
        switch (side){
            case LEFT:
                return Door.SIDE.RIGHT;
            case RIGHT:
                return Door.SIDE.LEFT;
            case UP:
                return Door.SIDE.DOWN;
            case DOWN:
                return Door.SIDE.UP;
            default:
                throw new RuntimeException("Exception in opposites rooms");
        }
    }

}
