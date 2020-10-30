package com.github.liaojiacan.背包9讲._03多重背包;

/**
 * 多重背包，多重背包跟完全背包的定义类似，多了一个约束条件s[i], 代表第i件物品最多只能选s[i]件
 * 定义 dp[i][j]: 考虑前i个物品在容量为j的背包中可达到的最大总价值
 * 状态: 1. 不选择。dp[i][j] = dp[i-1][j];
 * 2. 选择。 dp[i,j] = max(dp[i-1][j-v[i]]+w[i], ..., dp[i-1][j-k*v[i]]+k*w[i]) | k < s[i] && j > k*v[i]
 *
 * @author liaojiacan
 * @date 2020/10/30
 * @desc
 */
public class Solution {


    public static void main(String[] args) {
        int[] volume = {0, 1, 2, 3, 4};
        int[] worth = {0, 2, 4, 4, 5};
        int[] size = {0, 3, 1, 3, 2};
        int maxVolume = 5;
        int ans = mKnapsackV2(volume, worth,size, maxVolume);
        System.out.println(ans);
    }

    private static int mKnapsack(int[] volume, int[] worth, int size[] ,int maxVolume){
        int N = volume.length - 1;
        int V = maxVolume;
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                //不选
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k * volume[i] <= j && k <= size[i]; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * volume[i]] + k * worth[i]);
                }
            }
        }
        return dp[N][V];
    }

    /**
     * 空间优化，这里的dp[i][j] 用到了 dp[i-1] 。 可以参考01背包的优化方法。
     * 将第二个循环 倒序执行，每次执行到的dp值就是未被更新的值，也就是i-1时计算的。
     * @param volume
     * @param worth
     * @param size
     * @param maxVolume
     * @return
     */
    private static int mKnapsackV2(int[] volume, int[] worth, int size[] ,int maxVolume){
        int N = volume.length - 1;
        int V = maxVolume;
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= volume[i]; j--) {
                for (int k = 1; k * volume[i] <= j && k <= size[i]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * volume[i]] + k * worth[i]);
                }
            }
        }
        return dp[V];
    }
}
