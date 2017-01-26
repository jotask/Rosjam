package com.github.jotask.rosjam.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

/**
 * Util
 *
 * @author Jose Vives Iznardo
 * @since 26/01/2017
 */
public class Util {

    private Util(){ }

    private static Color randomColor(){
        float r = MathUtils.random(0f, 1f);
        float g = MathUtils.random(0f, 1f);
        float b = MathUtils.random(0f, 1f);
        return new Color(r, g, b, 1f);
    }

}
