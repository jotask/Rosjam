package com.github.jotask.rosjam.util;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * CollisionFilter
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public class CollisionFilter {

    public enum EENTITY { WALLS, PLAYER, ENEMY, BULLET, ITEM }

    public static class ENTITY {

        public static final short WALLS = 1;
        public static final short PLAYER = 2;
        public static final short ENEMY = 4;
        public static final short BULLET = 8;
        public static final short ITEM = 16;

    }

    private static class MASK {

        public static final short WALLS = -1;
        public static final short PLAYER = ENTITY.WALLS;
        public static final short ENEMY = ENTITY.WALLS;
        public static final short BULLET = ENTITY.WALLS;
        public static final short ITEM = ENTITY.WALLS  | ENTITY.PLAYER;

    }

    public static void setMask(FixtureDef fix, EENTITY entity){

        switch (entity) {
            case WALLS:
                set(fix, ENTITY.WALLS, MASK.WALLS);
                break;
            case PLAYER:
                set(fix, ENTITY.PLAYER, MASK.PLAYER);
                break;
            case ENEMY:
                set(fix, ENTITY.ENEMY, MASK.ENEMY);
                break;
            case BULLET:
                set(fix, ENTITY.BULLET, MASK.BULLET);
                break;
            case ITEM:
                set(fix, ENTITY.ITEM, MASK.ITEM);
                break;
        }

    }

    private static void set(FixtureDef fd, short category, short mask){
        fd.filter.categoryBits = category;
        fd.filter.maskBits = mask;
    }

}
