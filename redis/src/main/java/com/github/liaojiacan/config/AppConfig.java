package com.github.liaojiacan.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by liaojiacan on 2017/8/17.
 */
@Configuration
@ComponentScan(basePackages = { "com.github.liaojiacan"})
@ImportResource({ "classpath:redis-config.xml" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{ new ClassPathResource("application.properties") };
        propertySourcesPlaceholderConfigurer.setLocations(resources);
        return propertySourcesPlaceholderConfigurer;
    }
}