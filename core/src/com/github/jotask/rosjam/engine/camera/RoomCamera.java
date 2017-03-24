package com.github.jotask.rosjam.engine.camera;

import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * RoomCamera
 *
 * @author Jose Vives Iznardo
 * @since 26/01/2017
 */
public class RoomCamera extends Camera{

    public RoomCamera(float w, float h) {
        super(w, h);
    }

    @Override
    public void _update() { }

    public void moveTo(final Room room){
        this.position.set(room.getCenter(), Z);
        this.update();
    }

}
