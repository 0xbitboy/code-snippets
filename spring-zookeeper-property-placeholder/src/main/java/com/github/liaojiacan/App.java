package com.github.liaojiacan;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"spring.xml","applicationContext-beans.xml"});
        Object person = ctx.getBean("person");
        System.out.println(person);

    }
}
