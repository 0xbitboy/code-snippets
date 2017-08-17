package com.github.liaojiacan.lintcode.longestCommonSubstring;

/**
 * Created by liaojiacan on 2017/5/27.
 */
public class Solution {

    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here

        int lenA = A.length();
        int lenB = B.length();
        int longest = 0;

        for(int i=0;i<lenA;i++){
            for(int j=0;j<lenB;j++){
                int n=i;
                int m=j;
                int substLongest = 0;
                while (n<lenA && m<lenB){
                    System.out.println(A.charAt(n)+"_"+B.charAt(m));
                    if(A.charAt(n)==B.charAt(m)){
                        n++;
                        m++;
                        substLongest++;
                    }else {
                        break;
                    }
                }
                longest = Math.max(longest,substLongest);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int a = new Solution().longestCommonSubstring("ABCDEF","BCDE");
        System.out.println(a);
    }
}
