package com.github.jotask.rosjam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.Inputs;
import com.github.jotask.rosjam.engine.assets.Assets;
import com.github.jotask.rosjam.engine.debug.Debug;
import com.github.jotask.rosjam.util.Ref;

public class Rosjam extends ApplicationAdapter {

	private static Rosjam instance;

	public static final Rosjam get(){
		if(instance == null)
			throw new RuntimeException();
		return instance;
	}

	private SpriteBatch sb;
	private ShapeRenderer sr;

	private GameStateManager gsm;

	private Debug debug;

	private Assets assets;

	@Override
	public void create () {
		instance = this;

		new Inputs();

		this.assets = new Assets();
		this.assets.loadEverything();

		this.sb = new SpriteBatch();

		this.sr = new ShapeRenderer();
		this.sr.setAutoShapeType(true);

		this.gsm = new GameStateManager();

		this.debug = new Debug();

		Gdx.gl.glClearColor(1, 1, 1, 1);

	}

	@Override
	public void resize(int width, int height) { this.gsm.resize(width, height); }

	@Override
	public void render () {
		this.gsm.update();

		// Clears the buffer
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.gsm.render(sb);

		if(Ref.APP_DEBUG)
			this.debug.render(sb);

		if(Ref.DEBUG)
			this.gsm.debug(sr);

	}
	
	@Override
	public void dispose () {
		this.gsm.dispose();
		instance = null;
	}

	public final GameStateManager getGsm(){ return this.gsm; }

	public final SpriteBatch getSb() { return this.sb; }

	public Assets getAssets() { return this.assets; }

}
