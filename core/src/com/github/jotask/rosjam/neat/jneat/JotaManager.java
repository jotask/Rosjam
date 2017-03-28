package com.github.jotask.rosjam.neat.jneat;

import com.github.jotask.rosjam.neat.Neat;
import com.github.jotask.rosjam.neat.config.Config;
import com.github.jotask.rosjam.neat.engine.Factory;
import com.github.jotask.rosjam.neat.engine.weapon.Weapon;
import com.github.jotask.rosjam.neat.jneat.genetics.Genome;

import java.util.LinkedList;

/**
 * EntityManager
 *
 * @author Jose Vives Iznardo
 * @since 03/03/2017
 */
public final class JotaManager {

    private LinkedList<NeatEnemy> active;
    private LinkedList<NeatEnemy> disabled;

    public JotaManager() {
        this.active = new LinkedList<NeatEnemy>();
        this.disabled = new LinkedList<NeatEnemy>();

        final Factory factory = Neat.get().getFactory();

        final int population = new Integer(Jota.get().getConfig().get(Config.Property.POPULATION));
        final float threshold = new Float(Jota.get().getConfig().get(Config.Property.THRESHOLD));

        for(int i = 0; i < population; i++){
            final NeatEnemy e = factory.getNeatEnemy(threshold);
            final Weapon sword = factory.getSword();
            e.equip(sword);
            e.disable();
            this.disabled.add(e);
        }

    }

    public void clear(){
        for(final NeatEnemy n: active){
            n.disable();
            this.disabled.add(n);
        }
        this.active.clear();
    }

    public void spawn(final Genome genome){
        final NeatEnemy tmp = this.disabled.pollFirst();
        tmp.activate(genome);
        this.active.add(tmp);
    }

    public void dispose(){
        clear();
        for(final NeatEnemy e: disabled){
            e.kill();
        }
    }

    public LinkedList<NeatEnemy> getActive() { return active; }

    public void moveDisabled() {
        final int row = 3;
        final float spaceX = 1f;
        int c = 0;
        float spaceY = 7.5f;
        for(final NeatEnemy e: this.disabled){
            if(row <= c){
                spaceY--;
                c = 0;
            }
            float x = 26.5f + (spaceX * c++);
            float y = spaceY;
            e.getBody().setTransform(x, y, e.getBody().getAngle());
        }
    }

    public int manyActived(){ return this.active.size(); }

    public int manyDisabled(){ return this.disabled.size(); }

}
