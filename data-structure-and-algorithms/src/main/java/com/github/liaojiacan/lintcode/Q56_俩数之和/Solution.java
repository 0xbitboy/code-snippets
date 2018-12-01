package com.github.liaojiacan.lintcode.Q56_俩数之和;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see  <a href="https://www.lintcode.com/problem/two-sum/description"></a>
 *
 *
 *  这个解法只有当数字是有序的时候才行。
 */
public class Solution {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        Arrays.sort(numbers);
        int[] result = new int[2];
        for(int i=0;i<numbers.length;++i){

            int left = i;
            int right = numbers.length-1;
            int b = target-numbers[i];
            while(left<=right){
                int mid = (left+right+1)/2;
                if(numbers[mid]==b){
                    result[0] = i;
                    result[1] = mid;
                    return result;
                }
                if(numbers[mid]<b){
                    left = mid +1;
                }else {
                    right = mid -1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {1,0,-1};
        System.out.println(Arrays.toString(new Solution().twoSum(numbers,0)));
    }
}
