package com.github.liaojiacan.spring.aop.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liaojiacan
 * @date 2019/3/13
 */
public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("Interceptor:Before");
		Object res ;
		try{
			res = methodProxy.invokeSuper(o,objects);
		}catch (Throwable tr){
			System.out.println("Interceptor:handleError()");
			throw  tr;
		}finally {
			System.out.println("Interceptor:After");
		}
		return res;
	}
}
