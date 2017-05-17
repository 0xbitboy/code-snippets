package com.github.liaojiacan.sort;

import java.util.Arrays;

import static com.github.liaojiacan.sort.FunctionTp.*;

/**
 * 快排
 * Created by liaojiacan on 2017/5/17.
 */
public class QuickSort {




    public void sort(Comparable[] a,int low,int hight){

        if(low>=hight) return;
        int vi=partition(a,low,hight);
        sort(a,low,vi);
        sort(a,vi+1,hight);

    }

    /**
     * 切分，切分数 为 a[0]
     * @param a
     * @param low
     * @param hight
     * @return
     */
    public int partition(Comparable[] a,int low,int hight){

        Comparable v = a[low];

        int i=low;
        int j=hight+1;

        while (true){
            while (less(a[++i],v)){ if(i>=hight) break;}
            while (greater(a[--j],v)){ if(j<=low) break;}
            if(i>=j) break;
            //交换
            swap(a,i,j);
        }
        swap(a,low,j);
        return j;
    }



    public static void main(String[] args) {

        QuickSort quickSort = new QuickSort();

        Integer a[] = new Integer[]{12,3,55,3,2212,22,98,2,0};
        quickSort.sort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

}
