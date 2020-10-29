package com.github.liaojiacan.背包9讲._01背包;

/**
 *
 * @author liaojiacan
 * @date 2020/10/22
 * @desc
 */
public class Solution {

    public static void main(String[] args) {
        int[] volume = {0,1,2,3,4};
        int[] worth = {0,2,4,4,5};
        int maxVolume = 5;
        int ans = zeroOneKnapsack2(volume, worth, maxVolume);
        System.out.println(ans);
    }

    /**
     * 01背包, 二维dp解决
     * @param volume  物品的对应的重量
     * @param worth   物品对应的价值
     * @param maxVolume   总容量
     * @return
     */
    private static int zeroOneKnapsack(int[] volume, int[] worth, int maxVolume ){
        // 定义 dp[i][j] 表示当最大容量为j只考虑0..i个物品的时候，的最大价值。
        // 子问题： 在容量为j的情况下。考虑第i个物品，只有2种选择，选或者不选。
        // ===>>> 1. 选：dp[i][j] = dp[i-1][j - volume[i]]+ worth[i] (前提是有足够的空间放置，j - volume[i]>=0)
        // ===>>> 2. 不选: dp[i][j] = dp[i-1][j];
        // ===>>> 3. dp[i][j] = max(dp[i-1][j - volume[i]]+ worth[i],dp[i-1][j]);

        int N = volume.length-1;
        int V = maxVolume;

        int[][] dp = new int[N+1][V+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if(j - volume[i] >=0){
                    //放得下，有2种选择
                    dp[i][j] = Math.max(dp[i-1][j-volume[i]] + worth[i], dp[i-1][j]);
                }else {
                    //放不下，只有1种
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[N][V];
    }

    /***
     * 01背包，一维dp解决
     * 从上面的二维dp可以看出，求第i列的时候我们只需i-1列的值，dp[i][w]的最优解一定是包含 dp[i][w-1]的最优解， 故 dp[i][w] >= dp[i][w-1]
     * 因为我们要求的是 i=n时的最大价值。那么中间的状态我们只需要知道dp[i-1][w]的最大值就行了。
     * (
     *   dp[j]  = dp[i-1][j-w[i]] + w[i]
     *   因为我们要用到dp[j-w[i]]这个值， 那么我们只要保证dp[j-w[i]] 这个值是未被更新的即可，未被更新就表示是上一行 也就是i=1留下来的结果。
     *   所以第二个循环从大到小
     * )
     * 那么状态转移可以简化为
     * 定义 dp[w] 为 当背包容量为v时 的最大价值
     * 对于选择第i个物品，我们还是有两种选择，一种是选，一种是不选。dp[w] = max(dp[w-w[i]] + v[i], dp[w]);
     * @param volume
     * @param worth
     * @param maxWeight
     * @return
     */
    private static int zeroOneKnapsack2(int[] volume, int[] worth, int maxWeight){
        int n = volume.length-1;
        int w = maxWeight;
        int[] dp = new int[w+1];
        for (int i = 1; i <= n; i++) {
            for (int j = w; j >=  volume[i] ; j--) {
                    dp[j] = Math.max(dp[j-volume[i]] + worth[i], dp[j]);
            }
        }
        return dp[w];
    }
}
