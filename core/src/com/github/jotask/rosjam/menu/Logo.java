package com.github.jotask.rosjam.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.flowpowered.noise.Noise;
import com.flowpowered.noise.NoiseQuality;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.StateAssets;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.util.Util;

/**
 * Logo
 *
 * @author Jose Vives Iznardo
 * @since 24/03/2017
 */
public class Logo implements MenuObject {

    private final TextureRegion region;

    private final Vector2 position;

    private float scale;

    double noise = 0;

    private float angle;

    private final int seed;

    Logo(final Camera camera) {
        this.region = Rosjam.get().getAssets().getStateAssets().getRegion(StateAssets.Images.SPLASH);
        final float x = camera.viewportWidth * .5f - (this.region.getRegionWidth()  * .5f);
        final float y = camera.viewportHeight - 75f - (this.region.getRegionHeight() * .5f);
        this.position = new Vector2(x, y);
        this.angle = 0f;
        this.scale = 1f;

        this.seed = MathUtils.randomSign();

    }

    @Override
    public void update() {
        this.scale = (float) Noise.valueCoherentNoise3D(noise,0, 0, 23, NoiseQuality.FAST);
        this.scale = (float) Util.map(scale, -1f, 1f, 1, 2);

        final float max = 25;

        this.angle = (float) Noise.valueCoherentNoise3D(0,noise, 0, 23, NoiseQuality.FAST);
        this.angle = (float) Util.map(angle, -1f, 1f, -max, max);

        noise += 0.1;
    }

    @Override
    public void render(SpriteBatch sb) {
//        TextureRegion region, float x, float y, float originX, float originY, float width, float height,
//        float scaleX, float scaleY, float rotation
        float x = position.x;
        float y = position.y;
        float w = region.getRegionWidth();
        float h = region.getRegionHeight();
        sb.draw(region, x, y, w * .5f, h * .5f, w, h, this.scale, this.scale, this.angle);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.setColor(Color.RED);
        sr.rect(position.x, position.y, region.getRegionWidth(), region.getRegionHeight());
    }
}
