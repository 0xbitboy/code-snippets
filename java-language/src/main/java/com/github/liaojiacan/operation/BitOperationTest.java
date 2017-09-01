package com.github.liaojiacan.operation;

/**
 * Created by liaojiacan on 2017/8/26.
 */
public class BitOperationTest {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Double.MAX_VALUE-1.0 == Double.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println();
        System.out.println(Long.MAX_VALUE>>1);
        System.out.println(1<<20);

        long max= 1>>32;
        System.out.println(max);

        System.out.println((double)(max-1)>(double)(max-2));

    }
}
