package com.github.liaojiacan.coding.堆排序;

import java.util.Arrays;

/**
 * @author liaojiacan
 * @date 2021/3/2
 * @desc
 */
public class Solution {


    private static int parent(int i){
        return i/2;
    }

    private static int left(int i){
        return (i<<1) + 1;
    }

    private static int right(int i){
        return (i<<1) + 2;
    }

    private static void down(int[] array, int i, int length){
        if(i >= length || i < 0){
            return;
        }
        int left = left(i);
        int right = right(i);

        int p = i;
        if(left < length && array[left] > array[p]) p = left;
        if(right < length && array[right] > array[p]) p = right;
        if(p!=i){
            swap(array,p, i);
            down(array,p,length);
        }
    }

    private static void swap(int[] array, int i, int j ){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void sort(int[] array){
        // 从下到上，左到右 对每一层的节点进行调整。
        // 这里构造后就是一个大小为length的 大顶堆
        for (int i = array.length/2; i >=0 ; i--) {
            down(array,i,array.length);
        }
        // 取堆顶元素跟最后一个元素交换，然后长度减一，继续调整
        int len = array.length;
        while (len > 0){
            swap(array,0,len-1);
            down(array,0,--len);
        }
    }

    public static void main(String[] args) {
        int[] a= {1,2,3,7,8,6,3,4,6,5,22};
        sort(a);
        System.out.println(Arrays.toString(a));
    }


}
