package com.github.liaojiacan.pathfinding.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaojiacan
 * @date 2021/3/24
 * @desc
 */
public class Grid {
    public final static int[][] VECTOR = {{-1,0},{1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
    private Node[][] nodes;
    private int height;
    private int width;

    private Grid(int height, int width, Node[][] nodes){
        this.height = height;
        this.width = width;
        this.nodes = nodes;
    }

    public Grid(int height, int width, int[][] matrix){
        if(matrix !=null && matrix.length > 0){
            this.height = matrix.length;
            this.width = matrix[0].length;
        }else{
            this.height = height;
            this.width = width;
        }

    }

    private void initNodes(int height, int width, int[][] matrix){
        this.nodes = new Node[height][width];
        if(matrix != null){
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.nodes[i][j] = new Node(i,j);
                    this.nodes[i][j].walkable = (matrix[i][j] == 0);
                }
            }
        }else {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.nodes[i][j] = new Node(i,j);
                }
            }
        }
    }

    public Node getNodeAt(int x, int y){
        if(!isInside(x,y)) return  null;
        return this.nodes[x][y];
    }

    private boolean isInside(int x, int y){
        return x >=0 && x < height && y >=0 && y < width;
    }

    /**
     * 获取邻居节点
     * @param node
     * @param diagonalMovement
     * @return
     */
    public List<Node> getNeighbors(Node node, boolean diagonalMovement){
        List<Node> ans = new ArrayList<>(8);
        int v = diagonalMovement ? 8 : 4;
        for (int k = 0; k < v; k++) {
            int ni = node.x + VECTOR[k][0];
            int nj = node.x + VECTOR[k][1];
            if(isInside(ni,nj)&& nodes[ni][nj].walkable){
                ans.add(nodes[ni][nj]);
            }
        }
        return ans;
    }

    @Override
    public Grid clone(){
        Node[][] nodes = new Node[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                nodes[i][j] = this.nodes[i][j].clone();
            }
        }
        return new Grid(this.height,this.width,nodes);
    }

}
