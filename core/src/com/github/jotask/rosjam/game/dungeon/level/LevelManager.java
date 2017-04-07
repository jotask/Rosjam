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

    public final WorldManager worldManager;
    public final EntityManager entityManager;
    public final DungeonManager dungeonManager;

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
        if(nextRoom != null){
            nextRoom();
        }else if(completed){
            nextLevel();
            completed = false;
        }
    }

    @Override
    public void render(SpriteBatch sb) { dungeonManager.getDungeon().render(sb); }

    @Override
    public void debug(ShapeRenderer sr) {
        dungeonManager.getDungeon().debug(sr);
    }

    public void setCompleted(){
        this.completed = true;
    }

    public void nextLevel(){
        // Augment level
        level++;
        Game.get().getPlay().score.addScore(Score.FLOOR_CLEARED);

        // Delete everything
        entityManager.reset();
        worldManager.deleteDungeon();

        // Create Everything
        dungeonManager.nextLevel();
        EntityManager.get().getPlayer().goTo(dungeonManager.getDungeon().initialRoom);

    }

    public void nextRoom(final Door door){
        // TODO Check if is the door for next level
        if(door.isOpen())
            nextRoom = door;
    }

    private void nextRoom(){
        // TODO Check if is the door for next level
        dungeonManager.enterRoom(nextRoom);
        Camera camera = this.worldManager.getGame().getCamera();
        if(camera instanceof RoomCamera) {
            RoomCamera cam = (RoomCamera) camera;
            cam.moveTo(nextRoom.connected.self);
        }else{
            System.out.println(camera.getClass().toString());
        }
        nextRoom = null;
    }

    public int getLevel() {
        return level;
    }
}
