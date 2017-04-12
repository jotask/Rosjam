package com.github.jotask.rosjam.game.dungeon.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.camera.RoomCamera;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.Game;
import com.github.jotask.rosjam.game.InitialParameters;
import com.github.jotask.rosjam.game.Score;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.entity.Entity;
import com.github.jotask.rosjam.game.world.WorldManager;

/**
 * LevelManager
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class LevelManager extends Entity{

    final WorldManager worldManager;
    public final DungeonManager dungeonManager;

    final EntityManager entityManager;

    private int level;
    private boolean completed = false;

    private Door nextRoom;

    public LevelManager(WorldManager worldManager, final InitialParameters.Cfg cfg) {
        this.worldManager = worldManager;
        this.level = cfg.level;
        this.entityManager = EntityManager.get();
        this.dungeonManager = new DungeonManager(this, cfg);
    }

    @Override
    public void update() {
        if(this.nextRoom != null){
            this.nextRoom();
        }else if(this.completed){
            this.nextLevel();
            this.completed = false;
        }
    }

    @Override
    public void render(SpriteBatch sb) { this.dungeonManager.getDungeon().render(sb); }

    @Override
    public void debug(ShapeRenderer sr) {
        this.dungeonManager.getDungeon().debug(sr);
    }

    public void setCompleted(){
        this.completed = true;
    }

    public void nextLevel(){
        // Increase level
        this.level++;
        Game.get().getPlay().score.addScore(Score.FLOOR_CLEARED);

        // Delete everything
        this.entityManager.reset();
        this.worldManager.deleteDungeon();

        // Create Everything
        this.dungeonManager.nextLevel();
        EntityManager.get().getPlayer().goTo(this.dungeonManager.getDungeon().initialRoom);

    }

    public void nextRoom(final Door door){
        if(door.isOpen())
            this.nextRoom = door;
    }

    private void nextRoom(){
        this.dungeonManager.enterRoom(nextRoom);
        Camera camera = this.worldManager.getGame().getCamera();
        if(camera instanceof RoomCamera) {
            RoomCamera cam = (RoomCamera) camera;
            cam.moveTo(nextRoom.connected.self);
        }else{
            System.out.println(camera.getClass().toString());
        }
        this.nextRoom = null;
    }

    public int getLevel() {
        return level;
    }

}
