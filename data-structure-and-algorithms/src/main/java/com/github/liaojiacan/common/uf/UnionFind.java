package com.github.liaojiacan.common.uf;

/**
 * 并查集
 * @author liaojiacan
 * @date 2020/12/13
 * @desc
 */
public class UnionFind {


    private final int[] id;
    private int count;

    public UnionFind(int N) {
        id=new int[N];
        count = N;
    }

    /**
     * 分量数（不连同的集合数）
     * @return
     */
    public int count(){
        return count;
    }

    /**
     * 两个点是否是连通的或者说是否是在同一个集合中
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p ,int q){
        return find(p) == find(q);
    }

    /***
     * 找到触点 所在的集合
     * @param p
     * @return
     */
    public int find(int p){
        if( id[p] == 0){
            id[p] = p;
        }
        if (p != id[p]){
            id[p] = find(id[p]);
        }
        return id[p];
    }

    /**
     * 连接两个触点
     * @param p
     * @param q
     */
    public void union(int p ,int q){
        int r = find(p);
        int t = find(q);
        if(r == t){
            return;
        }
        id[r] = t;
        count --;
    }
}
