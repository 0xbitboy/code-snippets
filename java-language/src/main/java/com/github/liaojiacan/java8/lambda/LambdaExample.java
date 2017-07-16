package com.github.liaojiacan.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by liaojiacan on 2017/7/16.
 */
public class LambdaExample {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("A", "D", "B", "C");

        Collections.sort(names,(String a,String b)->a.compareTo(b));

        System.out.println(names);

    }
}
