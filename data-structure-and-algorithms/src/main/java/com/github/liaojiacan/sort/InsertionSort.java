package com.github.liaojiacan.sort;

import java.util.Arrays;

import static com.github.liaojiacan.sort.FunctionTp.*;

/**
 * 插入排序，小规模排序最优
 *
 * 左边是已经排序好的，在左边的区域找到一个位置插入右边的第一个元素
 * Created by liaojiacan on 2017/5/17.
 */
public class InsertionSort {

    public static void  sort(Comparable[] a){

        for (int i=1;i<a.length;i++){
            Comparable v=a[i];
            int j;
            for(j=i;j>0;j--){
                if(less(a[j],a[j-1])){
                    swap(a,j,j-1);
                }else {
                    break;
                }
            }
            a[j] = v;
        }

    }

    public static void main(String[] args) {
        Integer a[] = new Integer[]{12,3,55,3,2212,22,98,2,0};
        InsertionSort.sort(a);
        System.out.println(Arrays.toString(a));
    }


}
