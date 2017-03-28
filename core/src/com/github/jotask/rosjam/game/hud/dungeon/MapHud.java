package com.github.jotask.rosjam.game.hud.dungeon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.rosjam.Rosjam;
import com.github.jotask.rosjam.engine.camera.Camera;
import com.github.jotask.rosjam.game.dungeon.Dungeon;
import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.level.DungeonManager;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.LinkedList;

/**
 * MapHud
 *
 * @author Jose Vives Iznardo
 * @since 27/03/2017
 */
class MapHud {

    private DungeonHud dungeonHud;

    // FIXME camera problems

    // TODO Dispose
    private TextureRegion texture;

    private Rectangle rectangle;

    private final DungeonManager dungeonManager;

    private final Vector2 center;

    private Dungeon dungeon;

    MapHud(final DungeonHud dungeonHud) {
        this.dungeonHud = dungeonHud;
        final Camera c = this.dungeonHud.camera;
        float x = c.position.x;
        float y = c.position.y;
        this.center = new Vector2();
        this.rectangle = new Rectangle(x, y, 10f, 10f);

        this.texture = Rosjam.get().getAssets().getDungeonAssets().getBackground();

        this.dungeonManager = dungeonHud.dungeonState.getLevel().dungeonManager;
        this.dungeon = this.dungeonManager.getDungeon();

        this.resizeMap();

    }

    public void update(){
        if(this.dungeon != this.dungeonManager.getDungeon()){
            this.dungeon = this.dungeonManager.getDungeon();
            this.resizeMap();
        }
    }

    public void render(final SpriteBatch sb){

        for(final Room r: this.dungeon.getRooms()){
            room(sb, r);
        }

    }

    private void resizeMap(){

        System.out.println("----------------");

        this.center.setZero();

        final  LinkedList<Room> open = new LinkedList<Room>();
        final LinkedList<Room> visited = new LinkedList<Room>();
        open.add(this.dungeon.initialRoom);

        final Rectangle b = new Rectangle(0,0,Room.WIDTH,Room.HEIGHT);

        Room current;
        while(!open.isEmpty()){
            current = open.poll();
            visited.add(current);

            final Rectangle r = current.getBounds();

            if(!b.contains(r.getCenter(new Vector2()))){

                System.out.println(b.toString() + " : " + r.toString());

                if(r.x < b.x){
                    b.x -= Room.WIDTH;
                }else if(!b.contains(r.x + r.width, Room.HEIGHT * .5f)){
                    b.width += Room.WIDTH;
                }
//                else if(r.width > b.x + r.x + r.width){
//                    b.width += Room.WIDTH;
//                }
//                if(r.y < b.y){
//                    b.y -= Room.HEIGHT;
//                }else if(r.height > b.y + r.y + r.height){
//                    b.height += Room.HEIGHT;
//                }
                System.out.println(b.toString());
                System.out.println("---");

//                if(r.y < b.y){
//                    b.y += r.height;
//                    System.out.println("y");
//                }else if(r.height > b.y + r.y + r.height){
//                    b.height -= r.height;
//                    System.out.println("h");
//                }

            }

            for(final Door d: current.doors){
                if(d.connected == null)
                    continue;
                final Room other = d.connected.self;
                if(other == null)
                    continue;
                if(!visited.contains(other)){
                    open.addLast(d.connected.self);
                }
            }
        }

        // FIXME
//        this.rectangle.set(0,0,0,0);
//        System.out.println(b.toString());
//        final float w = (b.getWidth() / Room.WIDTH) * Room.WIDTH;
//        final float h = 5;
//        this.rectangle.setSize(w, h);
//
//        System.out.println("New Bounds: " + this.rectangle.toString());
////        this.rectangle.set(b);
//
//        final Vector2 p = this.rectangle.getPosition(new Vector2());
//        p.x -= this.rectangle.getWidth() * .5f;
//        p.y -= this.rectangle.getHeight() * .5f;
//        this.rectangle.setPosition(p.x, p.y);

        this.rectangle.set(b);
        this.rectangle.getCenter(this.center);

    }

    private void room(final SpriteBatch sb, final Room room){

        final float offset = 1f;

//        if(!room.isEntered()) {
//            return;
//        }

        sb.draw(this.texture,
                center.x + room.bounds.x + offset,
                center.y + room.bounds.y + offset,
                Room.WIDTH - offset,
                Room.HEIGHT - offset
        );

    }

    public void debug(final ShapeRenderer sr){
        sr.setColor(Color.BROWN);
        sr.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        sr.set(ShapeRenderer.ShapeType.Line);
        for(final Room r: this.dungeon.getRooms()){
            drawRoom(r, sr);
        }
    }

    private void drawRoom(final Room r, ShapeRenderer sr){

        final float offset = 1f;
        if(!r.isEntered()) {
            return;
        }else if(r.isInside()){
            final Color c = Color.RED;
            sr.setColor(c);
        }else{
            final Color c = Color.BLACK;
            sr.setColor(c);
        }
        sr.rect(center.x + r.bounds.x + offset,
                center.y + r.bounds.y + offset,
                Room.WIDTH - offset,
                Room.HEIGHT - offset
        );
//        for(Door d: r.doors){
//            sb.draw(texture, d.getPosition().x * Room.WIDTH, r.bounds.y + d.getPosition().y, 1f, 1f);
//        }
    }

}
