package com.github.liaojiacan.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author liaojiacan
 * @date 2019/3/14
 */
public class MyIocBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyIocBeanPostProcessor:postProcessBeforeInitialization->"+beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyIocBeanPostProcessor:postProcessAfterInitialization->"+beanName);
		return bean;
	}
}
