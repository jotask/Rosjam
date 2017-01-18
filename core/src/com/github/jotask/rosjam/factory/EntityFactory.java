package com.github.jotask.rosjam.factory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.assets.PlayerAssets;
import com.github.jotask.rosjam.game.WorldManager;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.Player;
import com.github.jotask.rosjam.util.Sprite;

/**
 * EntityBuilder
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
public class EntityFactory {

    private EntityFactory() {

    }

    public static Player generatePlayer(final WorldManager worldManager, final Room room) {

        final Vector2 center = room.getCenter();

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(center.x, center.y);

        Body body = worldManager.getWorld().createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.5f, .5f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;

        Fixture fixture = body.createFixture(fd);

        shape.dispose();

        TextureRegion region = Rosjam.get().getAssets().getPlayerAssets().getRegion(PlayerAssets.SPRITE.DEFAULT);

        final Sprite sprite = new Sprite(region, body);

        Player player = new Player(body, worldManager.getGame().getController(), sprite);
        return player;
    }

}
