package com.github.liaojiacan.sort;

import java.util.Arrays;

import static com.github.liaojiacan.sort.FunctionTp.less;
import static com.github.liaojiacan.sort.FunctionTp.swap;

/**
 * 冒泡排序
 * Created by liaojiacan on 2017/5/17.
 */
public class BubbleSort {


    public static void sort(Comparable[] a){

        for(int i=0;i<a.length;i++){
            for(int j=1;j<a.length-i;j++){
                if(less(a[j],a[j-1])){
                    swap(a,j,j-1);
                }
            }
        }

    }

    public static void main(String[] args) {
        Integer a[] = new Integer[]{100,12,3,55,3,2212,22,98,2,0};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
