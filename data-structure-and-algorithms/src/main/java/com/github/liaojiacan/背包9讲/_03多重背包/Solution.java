package com.github.liaojiacan.背包9讲._03多重背包;

import java.util.ArrayList;
import java.util.List;

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
        int ans = mKnapsackV3(volume, worth,size, maxVolume);
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
     * T = O(N^3) . 是一个立方的时间复杂度。 N <= 100 才是可以接受的 。
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


    /**
     * 当数据规模为
     * 0<N≤1000
     * 0<V≤2000
     * 0<vi,wi,si≤2000
     * 时，上面的题解，的时间复杂度为 1000 * 2000* 2000 , 2*10^9次方。 已经超出了现在限制
     * ===== 二进制 优化 ======
     * 解决思路: 将多重背包问题转换为01背包。那么我们就可以简化掉一层循环。
     * 关键: 1. 对第i个物品我们最多只能选si个。 那么如果我们将这si个拆分成 k个物品。 比如第i个物品限制最多7个，那么我们可以拆分成4,3 或者 2,5.... 等。
     *      2. 对应任意个数 。可以拆分成 以2为底的数 之和。 比如 7 可以拆分成 6 + 1 或者 4 + 2 +  1;
     *
     *  时间复杂度 O(N*V*LOG(S))
     * @param volume
     * @param worth
     * @param size
     * @param maxVolume
     * @return
     */
    private static int mKnapsackV3(int[] volume, int[] worth, int size[] ,int maxVolume){


        int N = volume.length - 1;
        int V = maxVolume;
        int[] dp = new int[V + 1];
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int s = size[i];
            //Log(S)
            for (int k = 1; k <= s ; k*=2) {
                s -=k;
                goodsList.add(new Goods(k*volume[i], k*worth[i]));
            }
            if(s > 0){
                goodsList.add(new Goods(s*volume[i], s*worth[i]));
            }
        }

        // 0 1 背包处理
        N = goodsList.size();
        for (int i = 0; i < N; i++) {
            for (int j = V; j>=0 ; j--) {
                Goods goods = goodsList.get(i);
                if(j >= goods.v){
                    dp[j] = Math.max(dp[j], dp[j - goods.v] + goods.w);
                }
            }
        }
        return dp[V];
    }

    public static class Goods{
        public int v;
        public int w;

        public Goods(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
