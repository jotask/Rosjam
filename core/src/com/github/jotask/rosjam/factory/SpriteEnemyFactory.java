package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.EnemyAssets;
import com.github.jotask.rosjam.game.entity.enemy.Enemies;
import com.github.jotask.rosjam.util.Ref;
import com.github.jotask.rosjam.util.Sprite;

/**
 * SpriteEnemyFactory
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public final class SpriteEnemyFactory {

    public static Sprite get(final Enemies enemy, final Body body) {

        TextureRegion regions = Rosjam.get().getAssets().getEnemyAssets().getRegion(enemy);
        TextureRegion[][] region = regions.split(enemy.regionWidth, regions.getRegionHeight());

        TextureRegion[] reg = new TextureRegion[region.length * region[0].length];
        for(int i = 0; i < region.length; i ++) {
            for(int s = 0; s < region[0].length; s ++) {
                reg[(i * region.length) + s] = region[i][s];
            }
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, reg);

        animation.setFrameDuration(Ref.ANIMATION_FRAME);
        Sprite sprite = new Sprite(body, animation);
        return sprite;

    }

}
