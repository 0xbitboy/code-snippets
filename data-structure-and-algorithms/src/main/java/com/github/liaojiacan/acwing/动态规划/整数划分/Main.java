package com.github.liaojiacan.acwing.动态规划.整数划分;

import java.util.Scanner;

/**
 * @author liaojiacan
 * @date 2021/2/2
 * @desc
 * @href https://www.acwing.com/problem/content/902/
 */
public class Main {

    public static final  double mod = 10e9 + 7;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] dp = new int[n + 1][n + 1];

        // dp[i][j] 表示用j个数表示的和为i的方案数。
        // 集合: j个数中最小值为1 和 j个数中最小值大于1

        // 当j个数中最小值为1的, 去掉一个1，则 dp[i][j] = dp[i-1][j-1];
        // 当j个数中最小值大于1的，对每个数都减去1，则dp[i][j] = dp[i-1*j][j];
        // 合起来 dp[i][j] =  dp[i-1][j-1] + dp[i-1*j][j];
        // 边界
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = (int) (( dp[i - 1][j - 1] + dp[i - j][j]) % mod);
            }
        }
        // 最终的结果 就是，用1个数表示N,2个数表示N.... n个数表示N的所有方案数的和

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += dp[n][i];
        }

        System.out.println(ans);

    }

}
