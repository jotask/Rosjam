package com.github.jotask.rosjam.factory;

import com.github.jotask.rosjam.game.dungeon.door.Door;
import com.github.jotask.rosjam.game.dungeon.room.Room;

import java.util.*;

/**
 * Graphs
 *
 * @author Jose Vives Iznardo
 * @since 02/02/2017
 */
public class Graph {

    static class Node {
        int weight;
        Room room;
        LinkedList<Node> connected = new LinkedList<Node>();
        boolean visited;
    }

    static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            // Assume neither string is null. Real code should
            // probably be more robust
            // You could also just return x.length() - y.length(),
            // which would be more efficient.
            if (o1.weight > o2.weight)
                return -1;

            if (o1.weight < o2.weight)
                return 1;

            return 0;
        }
    }

    private PriorityQueue<Node> priorityQueue;

    public Graph(final Room room){
        NodeComparator comparator = new NodeComparator();
        this.priorityQueue = new PriorityQueue<Node>(20, comparator);

        Node node = new Node();
        node.weight = 0;
        node.room = room;
        node.visited = false;

        Stack<Node> stack = new Stack<Node>();
        stack.add(node);

        while (!stack.isEmpty()){

            Node current = stack.pop();

            for(Door d: current.room.doors){

                if(d.connected == null)
                    continue;

                if(alreadyVisited(d.connected.self))
                    continue;

                Node n = new Node();
                n.weight = current.weight + 1;
                n.room = d.connected.self;

                stack.add(n);

            }

            priorityQueue.offer(current);

        }

    }

    public LinkedList<Room> getFars(){
        LinkedList<Room> rooms = new LinkedList<Room>();
        while(!priorityQueue.isEmpty())
            rooms.add(priorityQueue.poll().room);
        return rooms;
    }

    private boolean alreadyVisited(final Room room){
        for(Node node: priorityQueue){
            if(node.room == room)
                return true;
        }
        return false;
    }

}
