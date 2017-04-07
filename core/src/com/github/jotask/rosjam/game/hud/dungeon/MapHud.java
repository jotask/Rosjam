package com.github.jotask.rosjam.game.hud.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.game.PauseState;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.level.DungeonManager;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.LinkedList;

/**
 * MapHud
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
public class MapHud {

    // FIXME camera problems

    private TextureRegion texture;

    private final DungeonManager dungeonManager;

    private final Vector2 center;

    private Dungeon dungeon;

    public MapHud(final PauseState pauseState) {
        final Camera c = pauseState.camera;

        this.center = new Vector2(c.position.x + c.viewportWidth * .5f, c.position.y + c.viewportHeight * .5f);
        this.center.setZero();

        this.texture = Rosjam.get().getAssets().getDungeonAssets().getBackground();

        this.dungeonManager = pauseState.getGame().getPlay().getLevel().dungeonManager;
        this.dungeon = this.dungeonManager.getDungeon();

        this.resizeMap();

    }

    public void update(){
        if(this.dungeon != this.dungeonManager.getDungeon()){
            this.dungeon = this.dungeonManager.getDungeon();
            this.resizeMap();
        }
    }

    public void render(final SpriteBatch sb){

        for(final Room r: this.dungeon.getRooms()){
            room(sb, r);
        }

    }

    private void room(final SpriteBatch sb, final Room room){

        final float offset = 1f;

//        if(!room.isEntered()) {
//            return;
//        }

        final float s = 2f;

        float x = center.x + room.bounds.x + offset;
        float y = center.y + room.bounds.y + offset;
        float w = Room.WIDTH - offset;
        float h = Room.HEIGHT - offset;

        sb.draw(this.texture, x * s, y * s, w * s, h * s);

    }

    private void resizeMap(){

        this.center.setZero();

        final  LinkedList<Room> open = new LinkedList<Room>();
        final LinkedList<Room> visited = new LinkedList<Room>();
        open.add(this.dungeon.initialRoom);

        Room current;
        while(!open.isEmpty()){
            current = open.poll();
            visited.add(current);

            for(final Door d: current.doors){
                if(d.connected == null)
                    continue;
                final Room other = d.connected.self;
                if(other == null)
                    continue;
                if(!visited.contains(other)){
                    open.addLast(d.connected.self);
                }
            }

        }

    }

}
