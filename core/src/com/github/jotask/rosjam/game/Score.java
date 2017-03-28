package com.github.jotask.rosjam.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.util.Timer;

/**
 * Score
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
public class Score {

    private final DungeonState dungeonState;
    private final Timer timer;
    private final Camera camera;
    private final BitmapFont font;
    private final long initialScore = 1000;

    private long score;

    public Score(final DungeonState dungeonState) {
        this.dungeonState = dungeonState;
        this.camera = dungeonState.getGame().getCamera();
        this.timer = new Timer(1f);
        this.score = this.initialScore;
        this.font = Rosjam.get().getAssets().getFont();
    }

    public void update() {
        if(this.timer.isPassed(true))
            this.score--;
    }

    public long getScore() { return score; }

}
