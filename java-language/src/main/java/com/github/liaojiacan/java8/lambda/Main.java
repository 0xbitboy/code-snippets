package com.github.liaojiacan.java8.lambda;

/**
 * Created by liaojiacan on 2017/7/16.
 */
public class Main {

    public static void test(FunctionInterfaceExample fn){
        fn.action();
    }

    public static void main(String[] args) {
        FunctionInterfaceExample fn=()->{System.out.print("1");};
        test(fn);
    }
}
