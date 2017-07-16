package com.github.liaojiacan.java8.接口的默认方法;

/**
 *
 *  Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法
 * Created by liaojiacan on 2017/7/16.
 */
public interface Formula {
    double calculate(int a);
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
