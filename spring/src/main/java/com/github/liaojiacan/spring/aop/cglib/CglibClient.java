package com.github.liaojiacan.spring.aop.cglib;

import com.github.liaojiacan.spring.aop.HelloService;
import com.github.liaojiacan.spring.aop.HelloServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author liaojiacan
 * @date 2019/3/13
 */
public class CglibClient {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HelloServiceImpl.class);
		enhancer.setCallback(new MyMethodInterceptor());
		HelloService helloService = (HelloService) enhancer.create();
		helloService.sayHello();
	}
}
