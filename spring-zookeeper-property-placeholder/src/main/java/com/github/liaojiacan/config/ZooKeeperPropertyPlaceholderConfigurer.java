package com.github.liaojiacan.config;


import com.google.common.collect.Maps;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * Created by liaojiacan on 2017/7/17.
 */
public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private Logger logger = LoggerFactory.getLogger(ZooKeeperPropertyPlaceholderConfigurer.class);
    /**
     * zookeeper服务器地址 的properties参数名,在properties文件中设置
     */
    private final String ZOOKEEPER_SERVERS = "zk.servers";
    /**
     * 需要读取的配置文件子zookeeper的路径
     */
    private final String ZOOKEEPER_CONFIG_PATH = "zk.config.path";
    /**
     * 膜人的 ZOOKEEPER_CONFIG_PATH 的值
     */
    private final String CONFIG_ROOT_PATH_DEFAULT = "/app/main";

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        String zkServers = props.getProperty(ZOOKEEPER_SERVERS);
        String configPath = props.getProperty(ZOOKEEPER_CONFIG_PATH,CONFIG_ROOT_PATH_DEFAULT);

        try {
            Map<String, String> customProperties = loadPropetisFromZookeeper(zkServers, configPath);
            props.putAll(customProperties);
        } catch (Exception e) {
           e.printStackTrace();
        }
        super.processProperties(beanFactoryToProcess, props);
    }

    private Map<String,String> loadPropetisFromZookeeper(String zkServers ,String configRootPath) throws Exception {
        if (StringUtils.isEmpty(zkServers)) {
            throw new Exception("Failed to get the 'zk.servers' property");
        }
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkServers).retryPolicy(retryPolicy).build();
        client.start();

        List<String> configs = client.getChildren().forPath(configRootPath);
        Map<String, String> propertiesMap = Maps.newHashMap();
        if(configs!=null && configs.size()>0){
            for(String config : configs){
                byte[] bytes = client.getData().forPath(configRootPath + "/" + config);
                if(bytes!=null){
                    Properties pro = new Properties();
                    pro.load(new ByteArrayInputStream(bytes));
                    Set<String> names = pro.stringPropertyNames();
                    for(String name : names){
                        propertiesMap.put(name,pro.getProperty(name));
                    }
                }
            }

        }

        return propertiesMap;

    }


}