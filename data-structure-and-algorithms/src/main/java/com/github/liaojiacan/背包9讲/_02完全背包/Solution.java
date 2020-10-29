package com.github.liaojiacan.背包9讲._02完全背包;

/**
 * 完全背包跟01背包的区别在于，完全背包是可以重复选择。
 * 定义 dp[i][j]: 考虑前i个物品在容量为j的背包中可达到的最大总价值
 * 状态: 1. 不选择。dp[i][j] = dp[i-1][j];
 * 2. 选择。 dp[i,j] = max(dp[i-1][j-v[i]]+w[i], ..., dp[i-1][j-k*v[i]]+k*w[i])
 *
 * @author liaojiacan
 * @date 2020/10/28
 * @desc
 */
public class Solution {


    public static void main(String[] args) {
        int[] volume = {0, 1, 2, 3, 4};
        int[] worth = {0, 2, 4, 4, 5};
        int maxVolume = 5;
        int ans = nKnapsackV2(volume, worth, maxVolume);
        System.out.println(ans);
    }


    private static int nKnapsack(int[] volume, int[] worth, int maxVolume) {
        int N = volume.length - 1;
        int V = maxVolume;
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                //不选
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k * volume[i] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * volume[i]] + k * worth[i]);
                }
            }
        }
        return dp[N][V];
    }

    /**
     * 优化版本， 递推公式的变换
     * (1). dp[i][j] = max(dp[i-1][j-v[i]]+w[i], dp[i-1][j-2*v[i]]+2*w[i] ,..., dp[i-1][j-k*v[i]]+k*w[i])
     * (2). dp[i][j-v[i]] = max(                 dp[i-1][j-2*v[i]]+w[i], ..., dp[i-1][j-k*v[i]]+(k-1)*w[i])
     * 由 (1) 和 (2) 推导到 dp[i][j] = max(dp[i][j], d[i][j-v[i]]+w[i])
     * @param volume
     * @param worth
     * @param maxVolume
     * @return
     */
    private static int nKnapsackV2(int[] volume, int[] worth, int maxVolume) {
        int N = volume.length - 1;
        int V = maxVolume;
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                //不选
                dp[i][j] = dp[i - 1][j];
                if(j >= volume[i]){
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-volume[i]]+worth[i]);
                }
            }
        }
        return dp[N][V];
    }

}
