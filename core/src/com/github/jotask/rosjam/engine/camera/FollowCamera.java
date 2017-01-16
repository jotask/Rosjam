package com.github.jotask.rosjam.engine.camera;

import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.game.entity.BodyEntity;
import com.github.jotask.rosjam.game.entity.Player;

/**
 * FollowCamera
 *
 * @author Jose Vives Iznardo
 * @since 15/01/2017
 */
public class FollowCamera extends Camera{

    private BodyEntity target;

    public FollowCamera() { }

    @Override
    public void _update() {
        if(target == null)
            return;
        Vector2 p = target.getBody().getPosition();
        this.position.set(p.x, p.y, Z);
        this.update();
    }

    public void setTarget(Player target) {
        this.target = target;
    }
}
