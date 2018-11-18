package com.github.liaojiacan.lintcode.判断尾数;

/**
 * @see https://www.lintcode.com/problem/judge-the-last-number/description?_from=ladder&&fromId=60
 * 1459. 判断尾数
 * 有一个01字符串str。其中只可能会出现三个单词，两个字节的单词10或者11，一个字节的单词0。判断字符串中最后一个单词的字节数。
 *
 * 样例
 * Give str="100". Return 1.
 *
 * Explanation:
 *
 * Str consists of two words, 10 and 0.
 * Give str="1110". Return 2.
 *
 * Explanation:
 * Str consists of two words, 11 and 10.
 * 注意事项
 * 字符串长度不超过100000。
 */
public class Solution {
    /**
     * @param str: the str
     * @return: the sum of bytes in the last word
     */
    public int judgeTheLastNumber(String str) {
        // Write your code here
        int i = 0;
        while(i<str.length()){
            char c = str.charAt(i);
            i = (c=='1'?i+2:i+1);
            if(i==str.length()-1){
                return 1;
            }
        }


        return 2;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().judgeTheLastNumber("1011"));
    }
}
