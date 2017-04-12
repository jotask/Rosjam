package com.github.jotask.rosjam.game.dungeon.level;

import com.github.jotask.rosjam.engine.camera.RoomCamera;
import com.github.jotask.rosjam.factory.DungeonFactory;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.InitialParameters;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.config.ConfigDungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;

/**
 * DungeonManager
 *
 * @author Jose Vives Iznardo
 * @since 25/01/2017
 */
public class DungeonManager {

    private RoomCamera camera;

    private Dungeon dungeon;

    private Room currentRoom;


    private final DungeonFactory dungeonFactory;

    public DungeonManager() {
        this.dungeonFactory = new DungeonFactory();
        camera = (RoomCamera) Game.get().getCamera();
    }

    void loadLevel(InitialParameters.Cfg cfg){
        final ConfigDungeon cd;
        if(cfg.level == 0) {
            cd = new ConfigDungeon();
        }else{
            cd = new ConfigDungeon(cfg);
        }
        dungeon = dungeonFactory.generateDungeon(cd);
        currentRoom = dungeon.initialRoom;
        currentRoom.enter();
        camera.moveTo(currentRoom);
    }

    void nextLevel(){
        final ConfigDungeon cfg = new ConfigDungeon(this.dungeon.cfg);
        dungeon = dungeonFactory.generateDungeon(cfg);
        currentRoom = dungeon.initialRoom;
        currentRoom.enter();
        camera.moveTo(currentRoom);
    }

    public Dungeon getDungeon() { return dungeon; }

    void enterRoom(final Door door){

        currentRoom.exit();

        currentRoom = door.connected.self;

        camera.moveTo(currentRoom);

        EntityManager.get().getPlayer().goTo(door);

        currentRoom.enter();

    }

}
