package com.github.liaojiacan.背包9讲._04混合背包;

import java.util.ArrayList;
import java.util.List;

/**
 * 对于多重背包, 可以将物品分组，对于不同的物品采用不同的状态转移方程
 *
 * @author liaojiacan
 * @date 2020/10/30
 * @desc
 */

public class Solution {


    public static void main(String[] args) {
        int[] volume = {0, 1, 2, 3, 4};
        int[] worth = {0, 2, 4, 4, 5};
        // 物品个数限制，s<0 代表01背包限制，s=0代表完全背包，2 > 0 代表多重背包
        int[] size = {0, -1, 1, 0, 2};
        int maxVolume = 5;
        int ans = mixKnapsack(volume, worth,size, maxVolume);
        System.out.println(ans);
    }

    private enum Type{
        ONE_ZERO,
        COMPLETE
    }

    private static class Goods{
        public Type type;
        public int v;
        public int w;

        public Goods(Type type, int v, int w) {
            this.type = type;
            this.v = v;
            this.w = w;
        }
    }

    private static int mixKnapsack(int[] volume, int[] worth, int size[] ,int maxVolume){
        //先处理输入，整理成2种物品类型
        int N = volume.length -1;
        int V = maxVolume;
        List<Goods> goodsList = new ArrayList<>(N * 2);
        for (int i = 1; i <= N; i++) {
            if(size[i] < 0 ){
                goodsList.add(new Goods(Type.ONE_ZERO,volume[i], worth[i]));
            }else if(size[i] == 0){
                goodsList.add(new Goods(Type.COMPLETE,volume[i],worth[i]));
            }else {
                int s = size[i];
                // 多重背包，采用二进制优化法转换成01背包
                for (int k = 1; k <= s; k*=2) {
                    s-=k;
                    goodsList.add(new Goods(Type.ONE_ZERO,k*volume[i],k*worth[i]));
                }
                if(s > 0){
                    goodsList.add(new Goods(Type.ONE_ZERO,s*volume[i],s*worth[i]));
                }
            }
        }

        // 状态转移
        int dp[] = new int[V+1];
        for(Goods goods : goodsList){
            switch (goods.type){
                case ONE_ZERO:
                    for (int j = V; j >=goods.v; j --) {
                        dp[j] = Math.max(dp[j], dp[j-goods.v] + goods.w);
                    }
                    break;
                case COMPLETE:
                    for (int j = goods.v; j <= V; j++) {
                        dp[j] = Math.max(dp[j], dp[j-goods.v] + goods.w);
                    }
                    break;
                default:
                    break;
            }
        }

        return dp[V];

    }

}
