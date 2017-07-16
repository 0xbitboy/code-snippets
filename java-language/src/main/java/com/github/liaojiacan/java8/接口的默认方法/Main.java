package com.github.liaojiacan.java8.接口的默认方法;

/**
 * Created by liaojiacan on 2017/7/16.
 */
public class Main {

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));     // 100.0
        System.out.println(formula.sqrt(16));           // 4.0
    }
}
