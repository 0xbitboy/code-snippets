package com.github.liaojiacan.leetcode.Q56_俩数之和;

import java.util.Arrays;

/**
 * @see  <a href="https://www.lintcode.com/problem/two-sum/description"></a>
 *  2个指针
 *  这个解法只有当数字是有序的时候才行。
 */
public class Solution2 {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        Arrays.sort(numbers);
        int[] result = new int[2];
        int left =0 ;
        int right = numbers.length -1;
        while (left<right){
           int sum =  numbers[left]+numbers[right];
            if(sum ==target){
                result[0] = left;
                result[1] = right;
                return result;
            }
            if(sum<target){
                left++;
            }else {
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {1,0,-1};
        System.out.println(Arrays.toString(new Solution2().twoSum(numbers,0)));
    }
}
