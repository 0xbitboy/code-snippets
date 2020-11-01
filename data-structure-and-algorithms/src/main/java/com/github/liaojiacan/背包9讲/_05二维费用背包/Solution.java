package com.github.liaojiacan.背包9讲._05二维费用背包;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 二维费用的背包问题
 * 之前的选择物品的代价只考虑体积，现在在加入一个限制就是重量的限制。
 * 定义dp[i][j] 为体积为i时总量为j时的最大价值。
 * @author liaojiacan
 * @date 2020/11/1
 * @desc
 */
public class Solution {



    private static class Goods{
        // 体积
        public int volume;
        // 价值
        public int worth;
        // 重量
        public int weight;

        public Goods(int volume, int weight, int worth) {
            this.volume = volume;
            this.worth = worth;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {

        List<Goods> goodsList = Lists.newArrayList(
          new Goods(1,2,3),
          new Goods(2,4,4),
          new Goods(3,4,5),
          new Goods(4,5,6)
        );
        int maxVolume = 5;
        int maxWeight = 6;
        int ans = doubleCostKnapsack(goodsList, maxVolume, maxWeight);
        System.out.println(ans);
    }

    private static int doubleCostKnapsack(List<Goods> goodsList, int maxVolume, int maxWeight) {
        int[][] dp = new int[maxVolume+1][maxWeight+1];

        for (Goods goods: goodsList){
            for (int i = maxVolume; i >= goods.volume ; i--) {
                for (int j = maxWeight; j >= goods.weight; j--) {
                    dp[i][j]  = Math.max(dp[i][j], dp[i-goods.volume][j-goods.weight] + goods.worth);
                }
            }
        }
        return dp[maxVolume][maxWeight];
    }
}
