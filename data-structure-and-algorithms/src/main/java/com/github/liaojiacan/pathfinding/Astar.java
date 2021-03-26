package com.github.liaojiacan.pathfinding;

import com.github.liaojiacan.pathfinding.base.Grid;
import com.github.liaojiacan.pathfinding.base.Node;

import java.util.*;

/**
 * @author liaojiacan
 * @date 2021/3/24
 * @desc
 */
public class Astar {

    final static Comparator<Node> cp  = (node1,node2)->{return (int) (node1.f - node2.f);};
    final  static double SQRT2 = Math.sqrt(2);
    private static List<Node> backtrackPath(Node node){
        List<Node> res = new ArrayList<>();
        while (node!=null){
            res.add(node);
            node=node.parent;
        }
        Collections.reverse(res);
        return res;
    }
    public static List<Node> pathFinding(Node src, Node target, Grid grid){
        PriorityQueue<Node> openList = new PriorityQueue<>(cp);
        src.opened = true;
        openList.add(src);
        while (!openList.isEmpty()){
            Node node = openList.poll();
            node.closed = true;

            if(node == target){
                return backtrackPath(node);
            }

            List<Node> neighbors = grid.getNeighbors(node, true);
            for (Node nb : neighbors){
                if(nb.closed){
                    continue;
                }

                double ng = node.g + ((node.x - nb.x == 0 || node.y - nb.y == 0) ? 1 : SQRT2);
                // 如果是未搜索的，或者 当前的代价比之前的路径更优。
                if(!nb.opened || nb.g > ng){

                    nb.g = ng;
                    if(nb.h == 0)
                        nb.h = node.g + heuristic(Math.abs(nb.x - target.x), Math.abs(nb.y-target.y));
                    nb.f = nb.g + nb.h;
                    nb.parent = node;
                    if(!nb.opened){
                        nb.opened = true;
                        openList.add(nb);
                    }else {
                        openList.remove(node);
                        openList.add(node);
                    }
                }


            }
        }

        return Collections.emptyList();
    }

    private static double heuristic(int dx, int dy) {
        return dx + dy;
    }



}
