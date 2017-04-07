package com.github.jotask.rosjam.game.hud.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.HudAssets;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.game.DungeonState;

/**
 * DungeonHud
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
public class DungeonHud {

    protected final Camera camera;
    protected final BitmapFont font;
    protected final DungeonState dungeonState;
    protected final HudAssets asset;

    private final ScoreHud score;
    private final LifeHud life;
//    private final MapHud map;

    public DungeonHud(DungeonState dungeonState) {
        this.dungeonState = dungeonState;
        this.font = Rosjam.get().getAssets().getFont();
        this.asset = Rosjam.get().getAssets().getHudAssets();
        this.camera = new Camera(Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .5f);

        this.score = new ScoreHud(this);
        this.life = new LifeHud(this);
//        this.map = new MapHud(this);
    }

    public void update(){
        this.score.update();
        this.life.update();
//        this.map.update();
    }

    public void render(final SpriteBatch sb){
        sb.end();
        this.font.setColor(Color.BLACK);
        sb.setProjectionMatrix(this.camera.combined);
        sb.begin();
        this.score.render(sb);
        this.life.render(sb);
//        this.map.render(sb);
    }

    public void debug(final ShapeRenderer sr){
        sr.end();
        sr.setProjectionMatrix(this.camera.combined);
        sr.begin();
//        this.map.debug(sr);
    }

}
