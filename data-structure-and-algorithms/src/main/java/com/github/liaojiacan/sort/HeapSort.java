package com.github.liaojiacan.sort;

import java.util.Arrays;

import static com.github.liaojiacan.sort.FunctionTp.less;
import static com.github.liaojiacan.sort.FunctionTp.swap;

/**
 * Created by liaojiacan on 2017/5/19.
 */
public class HeapSort {


    public static  void sort(Comparable[] a){
        int N = a.length-1;
        for(int i=N/2;i>=1;i--){ //从非叶节点 的子树下沉。
            sink(a,i,N);
        }

        while (N>1){ //每次和最后一个节点交换 后 在顶部下沉。
            swap(a,1,N--);
            sink(a,1,N);
        }

    }


    /**
     * 节点k 下沉
     * 排序用的下沉算法 一般要满足 0开始的，二叉数的分布要改一下
     * Parent(i) = (i/2)
     * @param a
     * @param k
     * @param n
     */
    public static void sink(Comparable[] a,int k,int n){

        int j;
        while (2*k<=n){
            j=2*k;
            if(j<n  && less(a[j],a[j+1])) j++;
            if(!less(a[j],a[k])) {
                swap(a,j,k);
                k=j;
            }else {
                break;
            }

        }

    }

    public static void main(String[] args) {
        Integer a[] = new Integer[]{null,100,12,3,55,3,2212,22,98,2,0};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
