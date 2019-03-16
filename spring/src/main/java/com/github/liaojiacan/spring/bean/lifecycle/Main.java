package com.github.liaojiacan.spring.bean.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liaojiacan
 * @date 2019/3/14
 */
public class Main {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application_context.xml");

		MyBean myBean = (MyBean)applicationContext.getBean("myBean");
		applicationContext.destroy();
	}
}
