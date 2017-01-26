package com.github.jotask.rosjam.game.dungeon.level;

import com.github.jotask.rosjam.engine.camera.RoomCamera;
import com.github.jotask.rosjam.factory.DungeonFactory;
import com.github.jotask.rosjam.game.DungeonState;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.Player;

/**
 * DungeonManager
 *
 * @author Jose Vives Iznardo
 * @since 25/01/2017
 */
public class DungeonManager {

    private RoomCamera camera;

    private Player player;
    private Dungeon dungeon;

    private Room currentRoom;

    public DungeonManager() {
        player = DungeonState.get().getPlayer();
        camera = (RoomCamera) Game.get().getCamera();
    }

    public void nextLevel(){
        dungeon = DungeonFactory.generateDungeon(new ConfigDungeon());
        currentRoom = dungeon.initialRoom;
        currentRoom.enter();
        camera.moveTo(currentRoom);
    }

    public Dungeon getDungeon() { return dungeon; }

    public void enterRoom(final Door door){

        currentRoom.exit();

        currentRoom = door.connected.self;

        camera.moveTo(currentRoom);

        player.goTo(door);

        currentRoom.enter();

    }


}
