package com.github.jotask.rosjam.game.hud.dungeon;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.game.Score;

/**
 * ScoreHud
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
class ScoreHud {

    private DungeonHud dungeonHud;

    private Score score;

    private final BitmapFont font;

    private GlyphLayout glyphLayout;

    private Vector2 position;

    private String text;

    ScoreHud(final DungeonHud dungeonHud) {
        this.dungeonHud = dungeonHud;
        this.font = this.dungeonHud.font;
        this.score = this.dungeonHud.dungeonState.score;
        this.glyphLayout = new GlyphLayout();
        final Camera c = this.dungeonHud.camera;
        float x = c.position.x;
        float y = c.position.y + ( c.viewportHeight * .5f);
        this.position = new Vector2(x, y);
        this.update();
    }

    public void update(){
        this.text = "score: " + this.score.getScore();
        this.glyphLayout.setText(this.font, this.text);
    }

    public void render(final SpriteBatch sb){
        this.font.draw(
                sb, this.text,
                this.position.x - (this.glyphLayout.width * .5f),
                this.position.y - (this.glyphLayout.height * .5f)
        );
    }

}
