package com.github.jotask.rosjam.neat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.rosjam.neat.config.LoadConfig;
import com.github.jotask.rosjam.neat.engine.EntityManager;
import com.github.jotask.rosjam.neat.engine.Factory;
import com.github.jotask.rosjam.neat.engine.WorldListener;
import com.github.jotask.rosjam.neat.engine.entity.Player;
import com.github.jotask.rosjam.neat.jneat.Jota;

public class Neat{

	private static Neat instance;
	public static Neat get(){ return instance; }

	World world;

	EntityManager entityManager;


	Factory factory;

	private Jota jota;

	private Player player;

	public Neat() {

		Neat.instance = this;

		this.entityManager = EntityManager.get();

		this.world = new World(new Vector2(), true);
		this.world.setContactListener(new WorldListener());

		this.factory = new Factory(this);

		this.factory.createWalls();

		this.player = factory.getPlayer();

		this.jota = new Jota(LoadConfig.loadDefault());

	}

	public void update(){
		this.world.step(Gdx.graphics.getDeltaTime(), 6, 3);
		this.player.update();
		this.jota.eval();
		this.entityManager.update();
		this.jota.learn();
	}

	public void dispose () {
		this.jota.dispose();
		this.world.dispose();
		EntityManager.get().dispose();
		Neat.instance = null;
	}

	public World getWorld() { return world; }

	public Factory getFactory() { return factory; }

	public Player getPlayer() { return player; }

	public Jota getJota() { return jota; }
}
