package com.github.jotask.rosjam.menu;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.flowpowered.noise.Noise;
import com.flowpowered.noise.NoiseQuality;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.StateAssets;
import com.github.jotask.rosjam.util.Util;

/**
 * Logo
 *
 * @author Jose Vives Iznardo
 * @since 24/03/2017
 */
public class Logo extends Image {

    double noise = 0;

    private final int seed;

    Logo() {
        super(Rosjam.get().getAssets().getStateAssets().getRegion(StateAssets.Images.SPLASH));
        this.seed = MathUtils.randomSign();
    }

    @Override
    public void act(final float delta) {

        float scale;
        scale = (float) Noise.valueCoherentNoise3D(noise,0, 0, seed, NoiseQuality.FAST);
        scale = (float) Util.map(scale, -1f, 1f, 1, 2);

        final float max = 25;

        float angle;

        angle = (float) Noise.valueCoherentNoise3D(0,noise, 0, seed, NoiseQuality.FAST);
        angle = (float) Util.map(angle, -1f, 1f, -max, max);

        noise += 0.1;

        this.setScale(scale);
        this.setRotation(angle);

    }

}
