package com.github.liaojiacan.sort;

import java.util.Arrays;

import static com.github.liaojiacan.sort.FunctionTp.less;
import static com.github.liaojiacan.sort.FunctionTp.swap;

/**
 * 选择排序
 * Created by liaojiacan on 2017/5/17.
 */
public class SelectionSort {


    public static void sort(Comparable[] a){

        for(int i=0;i<a.length;i++){
            int min = i;
            for(int j=i+1;j<a.length;j++){
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            swap(a,i,min);
        }

    }



    public static void main(String[] args) {
        Integer a[] = new Integer[]{100,12,3,55,3,2212,22,98,2,0};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
