package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.rosjam.game.entity.Entity;
import com.github.jotask.rosjam.game.entity.enemy.Enemy;
import com.github.jotask.rosjam.game.entity.player.Player;

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
        if(instance == null)
             new EntityManager();

        return instance;
    }

    private Player player;
    private LinkedList<Entity> entities;

    private EntityManager() {
        EntityManager.instance = this;
        entities = new LinkedList<Entity>();
    }

    void createPlayer(final Player player){
        if(this.player == null){
            this.player = player;
        }else{
            throw new RuntimeException("Player already existing");
        }
    }

    public void dispose() {
        instance = null;
    }

    public void update() {
        player.update();
        LinkedList<Entity> survive = new LinkedList<Entity>(entities);
        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.update();
            if(e.needsToDie()){

                if(e instanceof Enemy){
                    Enemy enemy = (Enemy) e;
                    enemy.getRoom().enemyDied(enemy);
                }

                e.die();
                survive.remove(e);

            }
        }
        entities = survive;
    }

    public void reset(){
        entities.clear();
    }

    public void render(SpriteBatch sb) {
        for(Entity e: entities){
            e.render(sb);
        }
        player.render(sb);
    }

    public void debug(ShapeRenderer sr) {
        for(Entity e: entities){
            e.debug(sr);
        }
        player.debug(sr);
    }

    public static void add(Entity entity){ instance.addLocal(entity); }

    private void addLocal(Entity entity){
        entities.add(entity);
    }

    public int getSize(){ return this.entities.size(); }

    public Player getPlayer() {
        return player;
    }

}
