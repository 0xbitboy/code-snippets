# Spring 和 quartz整合
    
    quartz spring 配置
 
- [quartz](https://github.com/quartz-scheduler/quartz) 
- [文档](http://www.quartz-scheduler.org/documentation/best-practices.html)

## maven
  ```
     <dependency>
        <groupId>org.quartz-scheduler</groupId>
        <artifactId>quartz</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>org.quartz-scheduler</groupId>
        <artifactId>quartz-jobs</artifactId>
        <version>2.3.0</version>
    </dependency>
    
    <--spring xml bean 配置需要-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>4.3.3.RELEASE</version>
    </dependency>
  ```
  
## spring application xml
```
    <!-- Quartz -->
    <bean name="quartzTest" class="com.github.liaojiacan.task.MyTask" />
    <bean id="quartzTestJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <property name="targetObject" ref="quartzTest"></property>
        <property name="targetMethod" value="run"></property>
        <property name="concurrent" value="false"></property>
    </bean>

    <bean id="quartzTestTrigger" class="org.springframework.scheduling.quartz.SimpleTriggerFactoryBean">
        <property name="jobDetail" ref="quartzTestJob"/>
        <!-- 20秒后运行 -->
        <property name="startDelay" value="20000" />
        <!-- 每隔三十秒重复 -->
        <property name="repeatInterval" value="30000" />
    </bean>

    <bean name="quartzScheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <list>
                <ref bean="quartzTestTrigger"></ref>
             </list>
        </property>
    </bean>
```
