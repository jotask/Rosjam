package com.github.jotask.rosjam.game;

import com.github.jotask.rosjam.util.Timer;

/**
 * Score
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
public class Score {

//    Positive points:
//            + 200 points per floor cleared
//+400 points for sheol / cathedral / chest / dark room
//+666 points for boss rush / mega satan
//+1 point swag bonus for each held pickup on completion/death (bombs, keys, pennies)
//+??? for secret bonus points
//——–
//    Negative points:
//            -1 point per second spent playing (rewards for fast play)
//-10 per hit taken from an enemy / obstacles (damage from items/donations/etc don’t count as damage) (rewards for how well you play)
//            -?? per item taken (still testing this but i want people to be a bit more choosy on what they take and push risk reward a bit more here)

    public static final int FLOOR_CLEARED = 200;
    public static final int SECOND_PENALIZATION = -1;
    public static final int HIT_BY_ENEMY = -10;

    private final Timer timer;
    private final long initialScore = 627;

    private long score;

    public Score() {
        this.timer = new Timer(1f);
        this.score = this.initialScore;
    }

    public void update() {
        if(this.timer.isPassed(true))
            this.addScore(SECOND_PENALIZATION);
    }

    public void addScore(final int scr){
        this.score += scr;
    }

    public long getScore() { return score; }

}
