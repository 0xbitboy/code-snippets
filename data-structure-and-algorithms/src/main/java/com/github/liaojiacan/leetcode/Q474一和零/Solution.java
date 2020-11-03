package com.github.liaojiacan.leetcode.Q474一和零;

//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
//
//
// 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
//
//
//
//
// 示例 1：
//
//
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于
//n 的值 3 。
//
//
// 示例 2：
//
//
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
//
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 600
// 1 <= strs[i].length <= 100
// strs[i] 仅由 '0' 和 '1' 组成
// 1 <= m, n <= 100
//
// Related Topics 动态规划


/**
 * @author liaojiacan
 * @date 2020/11/3
 * @desc
 */
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // T = 600 *100 * 100 ， O(k*m*n).  10^6 次方。在oj可接受的范围
        // 转换成一个 二维费用的 0，1背包问题，这里每个物品的价值都是1, 代价分表是 0 和 1的个数
        // 定义dp[i][j] 为 0 的个数为i时 ，1的个数为j时的最大子集长度。
        int dp[][] = new int[m+1][n+1];
        for (String str:strs){

            int cnt_0 = 0;
            for (char c: str.toCharArray()){
                if(c == '0')
                    cnt_0 ++ ;
            }
            int cnt_1 = str.length() - cnt_0;
            for (int i = m ; i >=cnt_0; i--) {
                for (int j = n ; j >=cnt_1 ; j--) {
                    dp[i][j]= Math.max(dp[i][j], dp[i-cnt_0][j-cnt_1] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
