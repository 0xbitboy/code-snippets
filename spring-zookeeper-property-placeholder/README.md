# spring-zookeeper-property-placeholder

  > 将spirng中的配置放到zookeeper中管理，一般对于分布式的项目比较有用。

## maven 
 ```
  <!-- ZooKeeper -->
      <dependency>
         <groupId>org.apache.curator</groupId>
         <artifactId>curator-framework</artifactId>
         <version>3.2.1</version>
     </dependency>
     <dependency>
         <groupId>org.apache.curator</groupId>
         <artifactId>curator-recipes</artifactId>
         <version>3.2.1</version>
     </dependency>
   <!-- Zookeeper -->
 ```
 
## spring.xml
> application.properties 配置的是本地属性
```
    <bean class="com.github.liaojiacan.config.ZooKeeperPropertyPlaceholderConfigurer">
        <property name="systemPropertiesModeName" value="SYSTEM_PROPERTIES_MODE_OVERRIDE"></property>
        <property name="ignoreResourceNotFound" value="true"></property>
        <property name="locations">
            <list>
                <value>classpath*:application.properties</value>
            </list>
        </property>
    </bean>
```

## application.properties
``` 
#zk的连接地址
zk.servers=192.168.31.182:2181
#配置的目录节点，该节点下面的子节点会被遍历读取文本 加入到spring的property
zk.config.path=/app/spring-zookeeper-property-placeholder
```
