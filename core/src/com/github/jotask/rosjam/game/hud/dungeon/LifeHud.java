package com.github.jotask.rosjam.game.hud.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.engine.assets.HudAssets;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.entity.player.Player;

/**
 * Life
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
class LifeHud {

    private DungeonHud dungeonHud;

    private Player player;

    private Vector2 position;

    LifeHud(final DungeonHud dungeonHud) {
        this.dungeonHud = dungeonHud;
        this.player = EntityManager.get().getPlayer();
        final Camera c = this.dungeonHud.camera;

        float x = c.position.x - (c.viewportWidth * .5f) + 10f;
        float y = c.position.y + ( c.viewportHeight * .5f) - 10f * 2f;
        this.position = new Vector2(x, y);
        this.update();
    }

    public void update(){ }

    public void render(final SpriteBatch sb){
        final int health = this.player.getCurrentHealth();
        int x = 0;
        for(int i = 1; i < this.player.getMAX_HEALTH(); i += 2){
            final TextureRegion region;
            if(health > i){
                 region = dungeonHud.asset.getRegion(HudAssets.SPRITE.FULL_HEARTH);
            }else if(health < i){
                region = dungeonHud.asset.getRegion(HudAssets.SPRITE.EMPTY_HEARTH);
            }else{
                region = dungeonHud.asset.getRegion(HudAssets.SPRITE.HALF_HEARTH);
            }
            sb.draw(region, position.x + x++ * region.getRegionWidth(), position.y);
        }
    }

}
