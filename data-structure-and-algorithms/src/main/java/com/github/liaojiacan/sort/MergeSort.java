package com.github.liaojiacan.sort;

import java.util.Arrays;

import static com.github.liaojiacan.sort.FunctionTp.less;

/**
 * @author liaojiacan
 * @date 2019/4/18
 */
public class MergeSort {


    public static void sort(Comparable[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    public static void mergeSort(Comparable[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    public static void merge(Comparable[] a, int left, int mid, int right) {

        Comparable[] tempArray = Arrays.copyOf(a, a.length);

        int l = left, r = mid + 1;
        int k = left;

        while (l <= mid && r <= right) {
            if (less(tempArray[l], tempArray[r])) {
                a[k++] = tempArray[l++];
            } else {
                a[k++] = tempArray[r++];
            }
        }
        while (l <= mid) {
            a[k++] = tempArray[l++];
        }

        while (r <= right) {
            a[k++] = tempArray[r++];
        }

    }

    public static void main(String[] args) {
        Integer a[] = new Integer[]{12, 3, 55, 3, 2212, 22, 98, 2, 0};
        MergeSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
