package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.editor.EditorState;
import com.github.jotask.rosjam.factory.EnemyFactory;
import com.github.jotask.rosjam.game.Spawner;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.entity.Entity;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;

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

    protected TextureRegion background;

    private boolean inside = false;
    private boolean entered = false;
    protected boolean completed;

    public LinkedList<Door> doors;
    public LinkedList<Spawner> spawner;
    public LinkedList<Enemy> enemies;

    public LinkedList<Entity> entities;

    private EditorState.Tile[][] layout;

    public final int id;

    public Room(final int id, final Vector2 p, TextureRegion background) {
        this.id = id;
        this.bounds = new Rectangle(p.x, p.y, WIDTH, HEIGHT);
        this.background = background;
        this.doors = new LinkedList<Door>();
        this.spawner = new LinkedList<Spawner>();
        this.enemies = new LinkedList<Enemy>();
        this.entities = new LinkedList<Entity>();
        this.completed = false;
        this.inside = false;
    }

    @Override
    public void update() { }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(background, bounds.x, bounds.y, bounds.width, bounds.height);
        for(Door d: doors){
            d.render(sb);
        }
        for(Entity e: entities){
            e.render(sb);
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

        for(final Door d: this.doors){
            d.reset();
        }

        if(spawner.isEmpty())
            setCompleted(true);

        if(!completed) {
            for (Spawner v : spawner) {
                Enemy enemy = EnemyFactory.spawn(this, v);
                enemies.add(enemy);
            }
            for(Door d: doors){
                d.setOpen(false);
            }
        }else{
            for(Door d: doors){
                d.setOpen(true);
            }
        }
        this.inside = true;
        this.entered = true;
    }

    public void exit(){
        for(Enemy e: enemies){
            e.despawn();
        }
        this.inside = false;
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

    public boolean isInside() { return inside; }

    public boolean isEntered() { return entered; }

    public void setLayout(EditorState.Tile[][] layout) { this.layout = layout; }
    public EditorState.Tile[][] getLayout() { return layout; }
}
