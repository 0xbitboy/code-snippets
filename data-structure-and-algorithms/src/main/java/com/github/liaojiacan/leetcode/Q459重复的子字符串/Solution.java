package com.github.liaojiacan.leetcode.Q459重复的子字符串;

/**
 * @author liaojiacan
 * @date 2020/12/11
 * @desc
 */
//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
//
// 示例 1:
//
//
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
//
//
// 示例 2:
//
//
//输入: "aba"
//
//输出: False
//
//
// 示例 3:
//
//
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
//
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean repeatedSubstringPattern(String s) {

        int[] next = new int[s.length()];
        computeNextArray(s, next);

        int len = s.length();
        // 字符串的最长前后缀长度就是next[len-1] + 1 , len-1 就是字符串末尾的字符的下标。
        // 字符串长度减去 最长的公共前后缀长度。生效的部分，要么是前缀的一部分要么就是前缀本身。
        // 如果这个剩余的长度可以整除字符串的长度，说明前后缀有重复部分且重复部分是可以被完整分割为子串。

        if (next[len - 1] != -1 && len % (len -( next[len - 1] +1)) == 0) {
            return true;
        }

        return false;

    }

    public void computeNextArray(String s, int[] next) {
        int k = -1;
        next[0] = -1;
        for (int i = 1; i < s.length(); i++) {
            while (k >= 0 && s.charAt(k + 1) != s.charAt(i)) {
                k = next[k];
            }
            if (s.charAt(k + 1) == s.charAt(i)) {
                k++;
            }
            next[i] = k;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().repeatedSubstringPattern("abcabcabc"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
