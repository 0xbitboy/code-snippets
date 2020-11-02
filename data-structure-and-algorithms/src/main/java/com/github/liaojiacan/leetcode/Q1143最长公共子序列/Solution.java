package com.github.liaojiacan.leetcode.Q1143最长公共子序列;

//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
//
//
// 若这两个字符串没有公共子序列，则返回 0。
//
//
//
// 示例 1:
//
// 输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace"，它的长度为 3。
//
//
// 示例 2:
//
// 输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
//
//
// 示例 3:
//
// 输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。
//
//
//
//
// 提示:
//
//
// 1 <= text1.length <= 1000
// 1 <= text2.length <= 1000
// 输入的字符串只含有小写英文字符。
//
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {

        // 定义dp[i][j] 为 1到i个字符的text1 字符串 和 1到j个字符的text2字符串最长公共子串。
        // 对于text1[i] 和 text2[j] 字符存在4种状态。
        // 1. 00  text1[i] 和 text2[j] 都不包含在 lcs中
        // 2. 01  text1[i] 包含在 lcs中， text2[j] 不包含在 lcs中
        // 3. 10  text1[i] 不包含在 lcs中， text2[j] 包含在 lcs中
        // 4. 11  text1[i] 和 text2[j] 都包含在 lcs中

        // 对应 00, 01 和10 可以合成一个跟大的集合，“至少有一个不在lcs中”
        // 我们是要求最大值，则00 这种情况可以直接忽略，或者说01或者10这种情况是一定大于等于00的

        // 状态转义
        // 1. text1[i] == text2[j] 则 dp[i][j] = dp[i-1][j-1] + 1;
        // 2. text1[i] != text2[j] 则 dp[i][j] = max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]);

        // 递归写法
        int ans = dp(text1,text2, text1.length()-1, text2.length()-1);
        return ans;
    }


    public  int dp(String text1, String text2, int  i , int j ){
        // 空串
        if(i < 0 || j < 0 ){
            return 0;
        }
        if(text1.charAt(i) == text2.charAt(j)){
            return dp(text1,text2,i-1,j-1) + 1;
        }

        return Math.max(dp(text1,text2,i,j-1),dp(text1,text2,i-1,j));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
