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
import com.github.jotask.rosjam.engine.graphics.DoorSprite;
import com.github.jotask.rosjam.util.Ref;
import com.github.jotask.rosjam.engine.graphics.Sprite;

/**
 * AnimationFactory
 *
 * @author Jose Vives Iznardo
 * @since 04/02/2017
 */
public class SpriteFactory {

    private SpriteFactory(){}

    public static DoorSprite getDoor(Door.SIDE side){

        final DungeonAssets assets = Rosjam.get().getAssets().getDungeonAssets();
        TextureRegion[] regions = new TextureRegion[5];

        switch (side){
            case DOWN:
                regions[0] = assets.get(Tiles.DOOR_IRON_TOP_01);
                regions[1] = assets.get(Tiles.DOOR_IRON_TOP_02);
                regions[2] = assets.get(Tiles.DOOR_IRON_TOP_03);
                regions[3] = assets.get(Tiles.DOOR_IRON_TOP_04);
                regions[4] = assets.get(Tiles.DOOR_IRON_TOP_05);
                break;
            case RIGHT:
                regions[0] = assets.get(Tiles.DOOR_IRON_RIGHT_01);
                regions[1] = assets.get(Tiles.DOOR_IRON_RIGHT_02);
                regions[2] = assets.get(Tiles.DOOR_IRON_RIGHT_03);
                regions[3] = assets.get(Tiles.DOOR_IRON_RIGHT_04);
                regions[4] = assets.get(Tiles.DOOR_IRON_RIGHT_05);
                break;
            case UP:
                regions[0] = assets.get(Tiles.DOOR_IRON_BOTTOM_01);
                regions[1] = assets.get(Tiles.DOOR_IRON_BOTTOM_02);
                regions[2] = assets.get(Tiles.DOOR_IRON_BOTTOM_03);
                regions[3] = assets.get(Tiles.DOOR_IRON_BOTTOM_04);
                regions[4] = assets.get(Tiles.DOOR_IRON_BOTTOM_05);
                break;
            case LEFT:
                regions[0] = assets.get(Tiles.DOOR_IRON_LEFT_01);
                regions[1] = assets.get(Tiles.DOOR_IRON_LEFT_02);
                regions[2] = assets.get(Tiles.DOOR_IRON_LEFT_03);
                regions[3] = assets.get(Tiles.DOOR_IRON_LEFT_04);
                regions[4] = assets.get(Tiles.DOOR_IRON_LEFT_05);
                break;
            default:
                throw new RuntimeException("Unknown Door type");
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, regions);
        animation.setFrameDuration(Ref.ANIMATION_FRAME);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        DoorSprite sprite = new DoorSprite(animation);
        return sprite;
    }

    public static DoorSprite getWoodDoor(Door.SIDE side){

        final DungeonAssets assets = Rosjam.get().getAssets().getDungeonAssets();
        TextureRegion[] regions = new TextureRegion[4];

        switch (side){
            case DOWN:
                regions[0] = assets.get(Tiles.DOOR_WOOD_TOP_01);
                regions[1] = assets.get(Tiles.DOOR_WOOD_TOP_02);
                regions[2] = assets.get(Tiles.DOOR_WOOD_TOP_03);
                regions[3] = assets.get(Tiles.DOOR_WOOD_TOP_04);
                break;
            case RIGHT:
                regions[0] = assets.get(Tiles.DOOR_WOOD_RIGHT_01);
                regions[1] = assets.get(Tiles.DOOR_WOOD_RIGHT_02);
                regions[2] = assets.get(Tiles.DOOR_WOOD_RIGHT_03);
                regions[3] = assets.get(Tiles.DOOR_WOOD_RIGHT_04);
                break;
            case UP:
                regions[0] = assets.get(Tiles.DOOR_WOOD_BOTTOM_01);
                regions[1] = assets.get(Tiles.DOOR_WOOD_BOTTOM_02);
                regions[2] = assets.get(Tiles.DOOR_WOOD_BOTTOM_03);
                regions[3] = assets.get(Tiles.DOOR_WOOD_BOTTOM_04);
                break;
            case LEFT:
                regions[0] = assets.get(Tiles.DOOR_WOOD_LEFT_01);
                regions[1] = assets.get(Tiles.DOOR_WOOD_LEFT_02);
                regions[2] = assets.get(Tiles.DOOR_WOOD_LEFT_03);
                regions[3] = assets.get(Tiles.DOOR_WOOD_LEFT_04);
                break;
            default:
                throw new RuntimeException("Unknown Door type");
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, regions);
        animation.setFrameDuration(Ref.ANIMATION_FRAME);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        DoorSprite sprite = new DoorSprite(animation);
        return sprite;
    }

    public static Sprite getPlayer(final Body body) {
        TextureRegion region = Rosjam.get().getAssets().getPlayerAssets().getRegion(PlayerAssets.SPRITE.DEFAULT);
        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, region);
        animation.setFrameDuration(Ref.ANIMATION_FRAME);
        Sprite sprite = new Sprite(body, animation);
        return sprite;
    }

    public static Sprite getBullet(final Body body) {
        TextureRegion region = Rosjam.get().getAssets().getBulletAssets().getRegion(BulletAssets.SPRITE.DEFAULT);
        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, region);
        animation.setFrameDuration(Ref.ANIMATION_FRAME);
        Sprite sprite = new Sprite(body, animation);
        return sprite;
    }

    public static DoorSprite getFinalDoor() {
        final DungeonAssets assets = Rosjam.get().getAssets().getDungeonAssets();
        TextureRegion[] regions = new TextureRegion[2];
        regions[0] = assets.get(Tiles.DOOR_NEXT_LEVEL_OPEN);
        regions[1] = assets.get(Tiles.DOOR_NEXT_LEVEL_CLOSE);
        Animation<TextureRegion> animation = new Animation<TextureRegion>(Ref.ANIMATION_SPEED, regions);
        animation.setFrameDuration(Ref.ANIMATION_FRAME);
        DoorSprite sprite = new DoorSprite(animation);
        return sprite;
    }

}
