package com.github.jotask.rosjam.game.dungeon.level;

import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.DungeonAssets;
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

    private Room previousRoom;
    private Room currentRoom;

    private final LevelManager levelManager;

    private final DungeonFactory dungeonFactory;

    public DungeonManager(LevelManager levelManager) {
        this.levelManager = levelManager;
        DungeonAssets assets = Rosjam.get().getAssets().getDungeonAssets();
        this.dungeonFactory = new DungeonFactory(levelManager.worldManager.getWorld(), assets);
        player = DungeonState.get().getPlayer();
        camera = (RoomCamera) Game.get().getCamera();
    }

    public void nextLevel(){
        dungeon = dungeonFactory.generateDungeon(new ConfigDungeon());
        currentRoom = dungeon.initialRoom;
        camera.moveTo(currentRoom);
    }

    public Dungeon getDungeon() { return dungeon; }

    public void enterRoom(final Door door){

        currentRoom.exit();
        previousRoom = currentRoom;

        currentRoom = door.connected.self;

        camera.moveTo(currentRoom);

        player.goTo(door);

        currentRoom.enter();

    }

}
