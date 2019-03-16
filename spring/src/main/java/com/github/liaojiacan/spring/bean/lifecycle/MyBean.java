package com.github.liaojiacan.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author liaojiacan
 * @date 2019/3/14
 */
public class MyBean implements InitializingBean,DisposableBean,BeanFactoryAware,BeanNameAware,ApplicationContextAware,SmartInitializingSingleton {


	private DependencyBean dependencyBean;

	public void initMethod(){
		System.out.println("lifecycle:initMethod");
	}

	public void destroyMethod(){
		System.out.println("lifecycle:destroyMethod");
	}

	@PostConstruct
	public void postConstruct(){
		System.out.println("lifecycle:postConstruct");
	}

	@PreDestroy
	public void preDestroy(){
		System.out.println("lifecycle:preDestroy");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("lifecycle:setBeanFactory");
	}

	@Override
	public void setBeanName(String s) {
		System.out.println("lifecycle:setBeanName");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("lifecycle:destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("lifecycle:afterPropertiesSet");
	}

	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("lifecycle:afterSingletonsInstantiated");

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("lifecycle:setApplicationContext");

	}

	public DependencyBean getDependencyBean() {
		return dependencyBean;
	}

	@Autowired
	public void setDependencyBean(DependencyBean dependencyBean) {
		this.dependencyBean = dependencyBean;
		System.out.println("lifecycle:setDependencyBean");

	}
}
