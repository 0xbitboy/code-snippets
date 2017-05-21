package com.github.liaojiacan.spring.aop;

/**
 * Created by liaojiacan on 2017/5/21.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("Hello !");
    }
}
