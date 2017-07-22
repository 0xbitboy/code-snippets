package com.github.liaojiacan.search;

/**
 * 一个100大小的数组 ，1~99 的数都存在，求哪个数是重复的。
 * Created by liaojiacan on 2017/7/22.
 */
public class FindDuplicateNum {


    /**
     * 解决方法1，如果1~99都存在，那么用数组的和 减去1~99的和 剩下的就是重复的数。
     * @param a
     * @return
     */
    private static int solution1(int[] a){
        int sum=0;
        for(int i=0;i<a.length;i++){
            sum=(sum+a[i]-i);
        }
        return sum;
    }

    /**
     * 利用 异或运算
     * 1^2^3^4M...=4M; 1^1=0 2^2=0。。。 4M^4M=0 ,0^k=k
     * @param a
     * @return
     */
    private static int solution2(int[] a){

        int r = 0;
        for(int i=0;i<a.length;i++){
         r = (r^a[i]);
        }
        return r;
    }

    /**
     * 布尔标记法
     * @param a
     * @return
     */
    private static int solution3(int[] a){

        int flag[]  = new int[100];
        int i;
        for( i =0;i<a.length;i++){
            if(flag[a[i]]==1){
                break;
            }
            flag[a[i]]=1;
        }
        return a[i];
    }



    public static void main(String[] args) {
        int a[] = {1,2,3,3};
        System.out.println(solution1(a));
        System.out.println(solution2(a));
        System.out.println(solution3(a));
    }


}
