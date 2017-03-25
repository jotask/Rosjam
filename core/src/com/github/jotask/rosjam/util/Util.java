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

    public static double map(double value, double low1, double high1, float low2, float high2){
        return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
    }

    public static float cutDecimals(final float n, int d){
        float t = 1f;
        for(int i = 0; i < d; i++){
            t *= 10f;
        }
        return Math.round(n * t) / t;
    }

}
