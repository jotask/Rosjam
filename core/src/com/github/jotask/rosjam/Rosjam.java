package com.github.jotask.rosjam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.assets.Assets;
import com.github.jotask.rosjam.engine.GameStateManager;
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

	private Assets assets;

	@Override
	public void create () {
		instance = this;

		this.assets = new Assets();
		this.assets.loadEverything();

		sb = new SpriteBatch();

		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		gsm = new GameStateManager();

	}

	@Override
	public void resize(int width, int height) { this.gsm.resize(width, height); }

	@Override
	public void render () {
		gsm.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);      //clears the buffer
		gsm.render(sb);
		if(Ref.DEBUG)
			gsm.debug(sr);
	}
	
	@Override
	public void dispose () {
		gsm.dispose();
		instance = null;
	}

	public final GameStateManager getGsm(){ return this.gsm; }

	public final SpriteBatch getSb() { return sb; }

	public Assets getAssets() { return assets; }

}
