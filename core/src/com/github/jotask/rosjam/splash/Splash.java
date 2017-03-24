package com.github.jotask.rosjam.splash;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.GameStateManager;
import com.github.jotask.rosjam.engine.assets.StateAssets;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.engine.states.CameraState;
import com.github.jotask.rosjam.util.Timer;

/**
 * Splash
 *
 * @author Jose Vives Iznardo
 * @since 13/01/2017
 */
public final class Splash extends CameraState {

    private final float TIME = 3;
    private final Timer timer;

    private TextureRegion region;

    private final Vector2 position;

    public Splash(Camera camera) {
        super(camera);
        this.region = Rosjam.get().getAssets().getStateAssets().getRegion(StateAssets.Images.SPLASH);
        final float x = this.camera.position.x - (this.region.getRegionWidth()  * .5f);
        final float y = this.camera.position.y - (this.region.getRegionHeight() * .5f);
        this.position = new Vector2(x, y);
        this.timer = new Timer(TIME);
    }

    @Override
    public void update() {
        if(this.timer.isPassed()){
            Rosjam.get().getGsm().changeState(GameStateManager.STATE.MENU);
        }else{
            this.camera.zoom -= .0075f;
            this.camera.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(region, position.x, position.y, region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.setColor(Color.RED);
        sr.rect(position.x, position.y, region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void dispose() {

    }

}
