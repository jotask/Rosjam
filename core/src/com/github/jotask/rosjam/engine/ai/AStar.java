package com.github.jotask.rosjam.engine.ai;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.rosjam.editor.EditorState;
import com.github.jotask.rosjam.game.EntityManager;
import com.github.jotask.rosjam.game.dungeon.room.Room;
import com.github.jotask.rosjam.game.entity.player.Player;
import com.github.jotask.rosjam.util.Timer;

import java.util.LinkedList;

/**
 * AStar
 *
 * @author Jose Vives Iznardo
 * @since 25/03/2017
 */
public class AStar extends ArtificialIntelligence {

    private final float SPEED = 50f;

    public static final int WIDTH = 21;
    public static final int HEIGHT = 11;

    private final boolean diagonal = false;

    private Node[][] nodes;

    private final Timer timer;

    Node start;
    Node end;

    final Room room;

    final Player player;

    public AStar(final Body body, final Room room) {
        super(body);
        this.room = room;

        this.player = EntityManager.get().getPlayer();

        this.timer = new Timer(.25f);

        this.nodes = new Node[WIDTH][HEIGHT];

        final float w = 1f;
        final float h = 1f;

        final Rectangle bounds = this.room.getBounds();
        for(int i = 0; i < nodes.length; i++){
            for(int j = 0; j < nodes[0].length; j++){
                final float x = bounds.x + (i * w);
                final float y = bounds.y + (j * h);
                nodes[i][j] = new Node(i, j, x, y, w, h);
            }
        }

        final EditorState.Tile[][] l = this.room.getLayout();
        for(int i = 0; i < nodes.length; i++){
            for(int j = 0; j < nodes[0].length; j++) {
                final Node n = nodes[i][j];
                final EditorState.Tile t = l[i][j];
                if(t == EditorState.Tile.ROCK || t == EditorState.Tile.WALL){
                    n.state = Node.STATE.OBSTACLE;
                }
            }
        }

        this.start = nodes[1][1];
        this.start.state = Node.STATE.START;

        this.end = nodes[WIDTH - 2][HEIGHT - 2];
        this.end.state = Node.STATE.TARGET;

    }

    @Override
    public void update(){
        if(this.timer.isPassed(true)) {
            this.restart();
            {
                this.start.state = Node.STATE.EMPY;
                this.start = getNode(this.body.getPosition());
                this.start.state = Node.STATE.START;
            }
            {
                this.end.state = Node.STATE.EMPY;
                this.end = getNode(this.player.getBody().getPosition());
                this.end.state = Node.STATE.TARGET;
            }
            this.calculate();
            this.body.setLinearVelocity(Vector2.Zero);
            this.body.setAngularVelocity(0);
            this.move();
        }else{
            this.dir.setZero();
        }
        this.body.applyForceToCenter(dir.scl(SPEED), true);
    }

    private void move(){
        final Node n = this.buildPath();
        int x = n.i - this.start.i;
        int y = n.j - this.start.j;
        if(x > 0){
            this.dir.add(1,0);
        }else if(x < 0){
            this.dir.add(-1, 0);
        }
        if(y > 0){
            this.dir.add(0, 1);
        }else if(y < 0){
            this.dir.add(0, -1);
        }

    }

    private Node getNode(final Vector2 from) {
        final Rectangle bounds = this.room.getBounds();
        float x = Math.abs(from.x - bounds.x);
        float y = Math.abs(from.y - bounds.y);
        int i = (int) (x / 1 );
        int j = (int) (y / 1 );
        return nodes[i][j];
    }

    @Override
    public void debug(ShapeRenderer sr) {
        for(int i = 0; i < nodes.length; i++){
            for(int j = 0; j < nodes[0].length; j++) {
                nodes[i][j].render(sr);
            }
        }
    }

    private void restart(){

        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++) {
                Node n = nodes[i][j];
                if(n.state == Node.STATE.OBSTACLE)
                    continue;
                n.state = Node.STATE.EMPY;
                n.parent = null;
            }
        }

    }

    private void calculate(){

        LinkedList<Node> open = new LinkedList<Node>();
        LinkedList<Node> clos = new LinkedList<Node>();

        open.add(start);

        Node current;

        while(!open.isEmpty()){

            current = getLowestF(open);

            if(current == end){
                break;
            }

            open.remove(current);
            clos.add(current);

            LinkedList<Node> neighbors = getNeighbors(current);

            for(Node n: neighbors){
                if(clos.contains(n))
                    continue;

                if(n.state == Node.STATE.OBSTACLE)
                    continue;

                int tmpG = current.g + heuristic(n, current);

                boolean isNewPath = false;

                if(open.contains(n)){
                    if(tmpG < n.g) {
                        n.g = tmpG;
                        isNewPath = true;
                    }
                }else{
                    n.g = tmpG;
                    open.add(n);
                    isNewPath = true;
                }

                if(isNewPath){
                    n.h = heuristic(n, end);
                    n.f = n.g + n.h;
                    n.parent = current;
                }

            }

        }

        buildPath();

    }

    private Node buildPath(){
        Node tmp = end;
        if(tmp.parent == null){
            return tmp;
        }
        if(tmp.parent.parent == null){
            return tmp.parent;
        }
        while(tmp.parent.parent != null){
            Node p = tmp.parent;
            if(p != end || p != start) {
                p.state = Node.STATE.PATH;
            }
            tmp = tmp.parent;
        }
        return tmp;
    }

    private LinkedList<Node> getNeighbors(final Node n){

        final LinkedList<Node> neighbors = new LinkedList<Node>();
        final int i = n.i;
        final int j = n.j;
        // Top
        if(j < HEIGHT - 1) neighbors.add(nodes[i][j + 1]);
        // Top Right
        if(diagonal)
            if(i < WIDTH - 1 && j < HEIGHT - 1) neighbors.add(nodes[i + 1][j + 1]);
        // Right
        if(i < WIDTH - 1) neighbors.add(nodes[i + 1][j]);
        // Bottom Right
        if(diagonal)
            if(i < WIDTH - 1 && j > 0) neighbors.add(nodes[i + 1][j - 1]);
        // Bottom
        if(j > 0) neighbors.add(nodes[i][j - 1]);
        // Bottom Left
        if(diagonal)
            if(i > 0 && j > 0) neighbors.add(nodes[i - 1][ j - 1]);
        // Left
        if(i > 0) neighbors.add(nodes[i - 1][j]);
        // Left Top
        if(diagonal)
            if(i > 0 && j < HEIGHT - 1) neighbors.add(nodes[i - 1][j + 1]);

        return neighbors;
    }

    private Node getLowestF(LinkedList<Node> nodes){
        Node lowest = nodes.getFirst();
        for(Node n: nodes){
            if(n.f < lowest.g ){
                lowest = n;
            }
        }
        return lowest;
    }

    public void render(final ShapeRenderer sr){
        for(int i = 0; i < nodes.length; i++){
            for(int j = 0; j < nodes[0].length; j++) {
                nodes[i][j].render(sr);
            }
        }
    }

    private int heuristic(Node a, Node b){ return Math.abs(a.i - b.i) + Math.abs(a.j - b.j); }

}