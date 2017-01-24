package com.github.jotask.rosjam.util;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * CollisionFilter
 *
 * @author Jose Vives Iznardo
 * @since 21/01/2017
 */
public class CollisionFilter {

    public enum EENTITY { WALLS, PLAYER, ENEMY, PLAYER_FRIEND, ENEMY_FRIEND, ITEM, DOOR }

    public static class ENTITY {

        public static final short WALLS = 1;
        public static final short PLAYER = 2;
        public static final short ENEMY = 4;
        public static final short PLAYER_FRIEND = 8;
        public static final short ENEMY_FRIEND = 16;
        public static final short ITEM = 32;
        public static final short DOOR = 64;

    }

    private static class MASK {

        public static final short WALLS = -1;
        public static final short PLAYER = ENTITY.WALLS | ENTITY.PLAYER | ENTITY.ENEMY | ENTITY.ENEMY_FRIEND | ENTITY.DOOR;
        public static final short ENEMY = ENTITY.WALLS | ENTITY.PLAYER | ENTITY.ENEMY | ENTITY.PLAYER_FRIEND;
        public static final short PLAYER_FRIEND = ENTITY.WALLS | ENTITY.ENEMY;
        public static final short ENEMY_FRIEND = ENTITY.WALLS | ENTITY.PLAYER;
        public static final short ITEM = ENTITY.WALLS  | ENTITY.PLAYER;
        public static final short DOOR = ENTITY.PLAYER;

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
            case PLAYER_FRIEND:
                set(fix, ENTITY.PLAYER_FRIEND, MASK.PLAYER_FRIEND);
                break;
            case ENEMY_FRIEND:
                set(fix, ENTITY.ENEMY_FRIEND, MASK.ENEMY_FRIEND);
                break;
            case ITEM:
                set(fix, ENTITY.ITEM, MASK.ITEM);
                break;
            case DOOR:
                set(fix, ENTITY.DOOR, MASK.DOOR);
                break;
        }

    }

    private static void set(FixtureDef fd, short category, short mask){
        fd.filter.categoryBits = category;
        fd.filter.maskBits = mask;
    }

}
