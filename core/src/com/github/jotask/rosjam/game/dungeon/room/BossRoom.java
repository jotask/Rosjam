package com.github.jotask.rosjam.game.dungeon.room;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * BossRoom
 *
 * @author Jose Vives Iznardo
 * @since 02/02/2017
 */
public class BossRoom extends Room {

    public BossRoom(Vector2 p, TextureRegion background) {
        super(p, background);
    }

    @Override
    public void render(SpriteBatch sb) {
        Color c = sb.getColor();
        sb.setColor(Color.RED);
        super.render(sb);
        sb.setColor(c);
    }

}
