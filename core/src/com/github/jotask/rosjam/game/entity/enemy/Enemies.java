package com.github.jotask.rosjam.game.entity.enemy;

import com.github.jotask.rosjam.game.entity.enemy.enemies.*;

/**
 * Enemies
 *
 * @author Jose Vives Iznardo
 * @since 08/02/2017
 */
public enum Enemies {

    RAT (Rat.class, 0, 0, 158, 9, 16, .4f),
    BAT (Bat.class, 0, 9, 209, 15, 21, .4f),
    GOBLIN_MELE (GoblinMele.class, 0, 24, 140, 18, 14, .4f),
    GOBLIN_MAGIC (GoblinMagic.class, 0, 42, 130, 18, 13, .4f),
    SKELETON (Skeleton.class, 0, 60, 140, 31, 14, .4f),
    SNAKE (Snake.class, 0, 91, 110, 12, 11, .4f);

    public final Class<? extends Enemy> aClass;
    public final int x, y, width, height, regionWidth;
    public final float radius;

    Enemies(Class<? extends Enemy> aClass, int x, int y, int width, int height, int regionWidth, float radius){
        this.aClass = aClass;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.regionWidth = regionWidth;
        this.radius = radius;
    }

}
