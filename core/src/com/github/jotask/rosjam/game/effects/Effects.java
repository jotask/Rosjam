package com.github.jotask.rosjam.game.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

import java.util.LinkedList;

/**
 * Effects
 *
 * @author Jose Vives Iznardo
 * @since 11/04/2017
 */
public class Effects implements Disposable{

    private int SIZE = 4;

    private final ParticleEffect explosion;

    private final LinkedList<ParticleEffect> active;
    private final LinkedList<ParticleEffect> disable;

    public Effects() {
        this.explosion = new ParticleEffect();
        this.explosion.load(Gdx.files.internal("particles/explosion.p"),Gdx.files.internal("particles/"));

        this.active = new LinkedList<ParticleEffect>();
        this.disable = new LinkedList<ParticleEffect>();
        for(int i = 0; i < SIZE; i++){
            this.disable.addLast(new ParticleEffect(this.explosion));
        }

    }

    public void explode(final Body body){

        if(this.disable.isEmpty())
            return;

        final ParticleEffect p = this.disable.getFirst();
        this.disable.remove(p);
        this.active.add(p);

        p.getEmitters().first().setPosition(body.getPosition().x, body.getPosition().y);

        p.start();
    }

    public void render(final SpriteBatch sb){
        for(int i = this.active.size() - 1; i >= 0; i--){
            final ParticleEffect p = this.active.get(i);
            p.draw(sb, Gdx.graphics.getDeltaTime());
            if(p.isComplete()){
                this.active.remove(p);
                this.disable.addLast(p);
            }
        }
    }

    @Override
    public void dispose() {
        this.explosion.dispose();
        for(final ParticleEffect a: this.active){
            a.dispose();
        }
        for(final ParticleEffect d: this.disable){
            d.dispose();
        }
    }
}
