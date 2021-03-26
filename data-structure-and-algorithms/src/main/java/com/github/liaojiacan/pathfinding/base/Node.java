package com.github.liaojiacan.pathfinding.base;

/**
 * @author liaojiacan
 * @date 2021/3/24
 * @desc
 */
public class Node {
    public int x;
    public int y;
    public boolean walkable;
    public boolean closed;
    public boolean opened;
    public double f;
    public double g;
    public double h;
    public Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.walkable = true;
    }

    @Override
    protected Node clone() {
        Node node = new Node(x, y);
        node.walkable = walkable;
        node.closed = closed;
        node.opened = opened;
        node.f = f;
        node.g = g;
        return node;
    }
}
