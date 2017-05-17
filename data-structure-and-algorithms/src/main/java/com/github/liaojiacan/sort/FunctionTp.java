package com.github.liaojiacan.sort;

/**
 * Created by liaojiacan on 2017/5/17.
 */
public class FunctionTp {


    public static boolean less(Comparable a,Comparable b){
        return  !(a.compareTo(b)>0);
    }

    public static boolean greater(Comparable a,Comparable b){
        return  !(a.compareTo(b)<0);
    }

    public  static void swap(Comparable[] arr,int a,int b){
        Comparable tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
