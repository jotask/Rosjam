package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.factory.EntityFactory;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.entity.Enemy;
import com.github.jotask.rosjam.game.entity.Entity;

import java.util.LinkedList;

/**
 * Room
 *
 * @author Jose Vives Iznardo
 * @since 14/01/2017
 */
public class Room extends Entity {

    public static final int WIDTH = 21;
    public static final int HEIGHT = 11;

    private Body body;

    public final Rectangle bounds;

    private TextureRegion background;

    private boolean completed;

    public LinkedList<Door> doors;
    public LinkedList<Vector2> spawner;
    public LinkedList<Enemy> enemies;

    public Room(final Vector2 p, TextureRegion background) {
        this.bounds = new Rectangle(p.x, p.y, WIDTH, HEIGHT);
        this.background = background;
        this.doors = new LinkedList<Door>();
        this.spawner = new LinkedList<Vector2>();
        this.enemies = new LinkedList<Enemy>();
        this.completed = false;
    }

    @Override
    public void update() { }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(background, bounds.x, bounds.y, bounds.width, bounds.height);
        for(Door d: doors){
            d.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr){
        sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        for(Door d: doors){
            d.debug(sr);
        }
//        for(Vector2 v: spawner){
//            sr.rect(v.x, v.y, 1f, 1f);
//        }
    }

    public Rectangle getBounds() { return bounds; }

    public final void setBody(Body body) {
        if(this.body != null)
            throw new RuntimeException("Body is not null in room");
        this.body = body;
    }

    public Vector2 getCenter(){
        Vector2 center = new Vector2();
        center.x = bounds.x + (bounds.width  / 2f);
        center.y = bounds.y + (bounds.height / 2f);
        return center;
    }

    public Body getBody() { return body; }

    public void enter(){
        if(!completed) {
            for (Vector2 v : spawner) {
                enemies.add(EntityFactory.createEnemy(v, this));
            }
            for(Door d: doors){
                d.setOpen(false);
            }
        }else{
            for(Door d: doors){
                d.setOpen(true);
            }
        }
    }

    public void exit(){
        for(Enemy e: enemies){
            e.despawn();
        }
    }

    public void enemyDied(Enemy enemy){
        this.enemies.remove(enemy);
        if(enemies.isEmpty()){
            setCompleted(true);
            for(Door d: doors){
                d.setOpen(true);
            }
        }
    }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

}
