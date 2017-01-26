package com.github.jotask.rosjam.game.dungeon.level;

import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.Player;
import com.github.jotask.rosjam.game.world.WorldManager;

/**
 * DungeonManager
 *
 * @author Jose Vives Iznardo
 * @since 25/01/2017
 */
public class DungeonManager {

    private WorldManager worldManager;
    private Dungeon dungeon;
    private Player player;
    private Camera camera;

    private Room actualRoom;

    public DungeonManager(){
        nextLevel();
    }

    public void nextLevel(){
        // Generate next level
//        moveToRoom(dungeon.initialRoom);
    }

    private void moveToRoom(final Door door){
        // Set player position
//        player.goTo(door);
        // Move camera


    }

    public void generateDungeon(){

    }

}
