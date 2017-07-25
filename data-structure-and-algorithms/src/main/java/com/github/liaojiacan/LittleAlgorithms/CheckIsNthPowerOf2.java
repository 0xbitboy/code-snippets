package com.github.liaojiacan.LittleAlgorithms;

/**
 * 检测是否2的N次方
 * Created by liaojiacan on 2017/7/25.
 */
public class CheckIsNthPowerOf2 {



    public static boolean check(int num){
        return  ((num)&(num-1))==0;
    }


    public static void main(String[] args) {
        System.out.println(check(2));
        System.out.println(check(3));
        System.out.println(check(4));
        System.out.println(check(6));
        System.out.println(check(8));
    }
}
