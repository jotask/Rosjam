package com.github.jotask.rosjam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.engine.GameStateManager;

public class Rosjam extends ApplicationAdapter {

	private SpriteBatch sb;
	private ShapeRenderer sr;

	private GameStateManager gsm;

	@Override
	public void create () {

		sb = new SpriteBatch();

		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		gsm = new GameStateManager();

	}

	@Override
	public void render () {
		gsm.update();
		gsm.render(sb);
		gsm.debug(sr);
	}
	
	@Override
	public void dispose () {
		gsm.dispose();
	}

}
