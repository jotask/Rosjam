package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.BulletAssets;
import com.github.jotask.rosjam.engine.assets.DungeonAssets;
import com.github.jotask.rosjam.engine.assets.PlayerAssets;
import com.github.jotask.rosjam.engine.assets.Tiles;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.util.DoorSprite;
import com.github.jotask.rosjam.util.Ref;
import com.github.jotask.rosjam.util.Sprite;

/**
 * AnimationFactory
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
public class SpriteFactory {

    private static Sprite finalDoor;

    private SpriteFactory(){}

    public static DoorSprite getDoor(Door.SIDE side){

        final DungeonAssets assets = Rosjam.get().getAssets().getDungeonAssets();
        TextureRegion[] regions = new TextureRegion[4];

        switch (side){
            case DOWN:
                regions[0] = assets.get(Tiles.DOOR_TOP_01);
                regions[1] = assets.get(Tiles.DOOR_TOP_02);
                regions[2] = assets.get(Tiles.DOOR_TOP_03);
                regions[3] = assets.get(Tiles.DOOR_TOP_04);
                break;
            case RIGHT:
                regions[0] = assets.get(Tiles.DOOR_RIGHT_01);
                regions[1] = assets.get(Tiles.DOOR_RIGHT_02);
                regions[2] = assets.get(Tiles.DOOR_RIGHT_03);
                regions[3] = assets.get(Tiles.DOOR_RIGHT_04);
                break;
            case UP:
                regions[0] = assets.get(Tiles.DOOR_BOTTOM_01);
                regions[1] = assets.get(Tiles.DOOR_BOTTOM_02);
                regions[2] = assets.get(Tiles.DOOR_BOTTOM_03);
                regions[3] = assets.get(Tiles.DOOR_BOTTOM_04);
                break;
            case LEFT:
                regions[0] = assets.get(Tiles.DOOR_LEFT_01);
                regions[1] = assets.get(Tiles.DOOR_LEFT_02);
                regions[2] = assets.get(Tiles.DOOR_LEFT_03);
                regions[3] = assets.get(Tiles.DOOR_LEFT_04);
                break;
            default:
                throw new RuntimeException("Unknown Door type");
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(Door.ANIMATION_SPEED, regions);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        DoorSprite sprite = new DoorSprite(animation);
        return sprite;
    }

    public static Sprite getEnemy(EnemyFactory.ENEMY enemy, final Body body){
        TextureRegion region = Rosjam.get().getAssets().getPlayerAssets().getRegion(PlayerAssets.SPRITE.SPIDER);
        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, region);
        Sprite sprite = new Sprite(body, animation);
        return sprite;
    }

    public static Sprite getPlayer(final Body body) {
        TextureRegion region = Rosjam.get().getAssets().getPlayerAssets().getRegion(PlayerAssets.SPRITE.DEFAULT);
        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, region);
        Sprite sprite = new Sprite(body, animation);
        return sprite;
    }

    public static Sprite getBullet(final Body body) {
        TextureRegion region = Rosjam.get().getAssets().getBulletAssets().getRegion(BulletAssets.SPRITE.DEFAULT);
        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, region);
        Sprite sprite = new Sprite(body, animation);
        return sprite;
    }

    public static DoorSprite getFinalDoor() {
        final DungeonAssets assets = Rosjam.get().getAssets().getDungeonAssets();
        TextureRegion[] regions = new TextureRegion[2];
        regions[0] = assets.get(Tiles.DOOR_NEXT_LEVEL_OPEN);
        regions[1] = assets.get(Tiles.DOOR_NEXT_LEVEL_CLOSE);
        Animation<TextureRegion> animation = new Animation<TextureRegion>(Door.ANIMATION_SPEED, regions);
        DoorSprite sprite = new DoorSprite(animation);
        return sprite;
    }
}
