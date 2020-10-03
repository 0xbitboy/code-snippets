package com.github.liaojiacan.leetcode.Q567字符串的排列;

/**
 * @author liaojiacan
 * @date 2019/7/19
 */
class Solution {

    public boolean checkInclusion(String s1, String s2) {
        // s1存储在dict[26]， 用一个ascii 26个字母，来表示，下标表示与a的ascii的差值，value表示个数。
        // s2 用固定窗口（s1的长度）的模式，对窗口内的字符进行个数的统计
        // 对于2个统计结果是否相同。

        if(s1.length() > s2.length()){
            return false;
        }

        int dict[] = countWords(s1,0,s1.length()-1);

        int win = s1.length();
        for(int i=0 ; i+win < s2.length() ; ++i){
            int counts[] = countWords(s2,i,i+win-1);

            if(match(dict,counts)){
                return true;
            }
        }

        return false;

    }
    private boolean match(int[] count1, int[] count2){
        for(int k =0 ; k < 26 ; ++k ){
            if(count1[k] != count2[k]){
                return false;
            }
        }
        return true;
    }
    private int[] countWords(String originStr, int l , int r){
        int[] dict = new int[26];
        for(int i=l ;i<=r;++i){
            dict[originStr.charAt(i)-'a']++;
        }
        return dict;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkInclusion("abc","dcda"));
    }
}