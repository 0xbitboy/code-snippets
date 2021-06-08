package com.github.liaojiacan.coding.有序数组的平方集合元素个数;

/**
 * @author liaojiacan
 * @date 2021/6/8
 * @desc
 */
public class Solution {


    public static void main(String[] args) {

        int[] arr = new int[]{-4,-4,-1,0,1,1,1,1,1,3,10,10,10};
        int i= 0 , j = arr.length-1;
        int ans = 0;
        while ( i < j){
            if(Math.abs(arr[i]) < Math.abs(arr[j])){
                while (i < j && arr[i] == arr[i+1]){
                    i++;
                }
                ans++;
                i++;
            }else if(Math.abs(arr[i]) == Math.abs(arr[j])){
                while (i < j && arr[i] == arr[i+1]){
                    i++;
                }
                while (i < j && arr[j-1] == arr[j]){
                    j--;
                }
                ans++;
            }else {
                while (i < j && arr[j-1] == arr[j]){
                    j--;
                }
                ans++;
                j--;
            }
        }
        System.out.println(ans);
    }
}
