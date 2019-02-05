package com.github.liaojiacan.leetcode.merge_two_sorted_arrays;

import java.util.Arrays;

public class Solution {
    /**
     * 合并两个排序的整数数组A和B变成一个新的数组。
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int aLen = A.length;
        int bLen = B.length;
        int []C = new int[aLen+bLen];

        int p=0 , pA = 0 , pB=0;

        if(A.length==0){
            return B;
        }
        if(B.length ==0){
            return A;
        }
        while (p<C.length){

            while (pA>= A.length && pB < B.length){
                C[p++] = B[pB++];
            }
             while (pB>= B.length && pA < A.length){
                 C[p++] = A[pA++];
            }

            while (pA<A.length && pB < B.length && A[pA]<=B[pB]){
                C[p++] = A[pA++];
            }
            while ( pA<A.length && pB < B.length &&  A[pA]>B[pB]){
                C[p++] = B[pB++];
            }

        }

        return C;
    }

    public static void main(String[] args) {
        int []A ={1,2,3,4};
        int []B = {2,4,5,6};
        int []C = new Solution().mergeSortedArray(A,B);

        System.out.printf(Arrays.toString(C));
    }


}