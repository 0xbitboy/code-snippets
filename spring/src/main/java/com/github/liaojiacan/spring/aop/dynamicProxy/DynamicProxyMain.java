package com.github.liaojiacan.spring.aop.dynamicProxy;

import com.github.liaojiacan.spring.aop.HelloService;
import com.github.liaojiacan.spring.aop.HelloServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by liaojiacan on 2017/5/21.
 */
public class DynamicProxyMain {

    public static void main(String[] args) {

        HelloService helloService = new HelloServiceImpl();

        LogHandler logHandler = new LogHandler(helloService);

        HelloService proxy = (HelloService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), helloService.getClass().getInterfaces(), logHandler);

        proxy.sayHello();

        
    }
}
