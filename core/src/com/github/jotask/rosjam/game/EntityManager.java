package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.rosjam.game.entity.Entity;

import java.util.LinkedList;

/**
 * EntityManager
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public class EntityManager implements Disposable{

    private static EntityManager instance;
    public static final EntityManager get(){
        if(instance == null){
             instance = new EntityManager();
        }
        return instance;
    }

    private LinkedList<Entity> entities;

    private EntityManager() {
        entities = new LinkedList<Entity>();
    }

    public void dispose() {
        System.out.println("EntityManager.dispose();");
        instance = null;
    }

    public void update() {
        LinkedList<Entity> survive = new LinkedList<Entity>(entities);
        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.update();
            if(e.needsToDie()){
                e.die();
                survive.remove(e);
            }
        }
        entities = survive;
    }

    public void reset(){

        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.die();
        }

        entities.clear();

    }

    public void render(SpriteBatch sb) {
        for(Entity e: entities){
            e.render(sb);
        }
    }

    public void debug(ShapeRenderer sr) {

    }

    public static void add(Entity entity) {
        instance.addd(entity);
    }

    private void addd(Entity entity){
        entities.add(entity);
    }

    public int getSize(){ return this.entities.size(); }

}
