package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Node
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
class Node {

    enum STATE{
        EMPY(Color.WHITE),
        CHECKED(Color.LIGHT_GRAY),
        PATH(Color.GREEN),
        OBSTACLE(Color.BLACK),
        TARGET(Color.CYAN),
        START(Color.BROWN);
        final Color c;
        STATE(Color c){
            this.c = c;
        }
    }

    final float WIDTH;
    final float HEIGHT;

    public final int i;
    public final int j;

    private final Vector2 pos;

    public STATE state;

    public int f = 0;
    public int g = 0;
    public int h = 0;

    public Node parent = null;

    public Node(final int i, final int j, final float x, final float y, final float width, final float height) {
        this.i = i;
        this.j = j;
        this.WIDTH = width;
        this.HEIGHT = height;
        pos = new Vector2(x, y);
        state = STATE.EMPY;
    }

    public void restart(){
        if(this.state == Node.STATE.OBSTACLE)
            return;
        this.state = Node.STATE.EMPY;
        this.parent = null;
    }

    public void render(final ShapeRenderer sr){
        sr.setColor(state.c);
        sr.set(ShapeRenderer.ShapeType.Filled);
        sr.rect(pos.x, pos.y, WIDTH, HEIGHT);
        sr.setColor(Color.BLACK);
        sr.set(ShapeRenderer.ShapeType.Line);
        sr.rect(pos.x, pos.y, WIDTH, HEIGHT);
    }

}
