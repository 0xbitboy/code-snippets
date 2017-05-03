package com.github.liaojiacan.memcache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeoutException;

/**
 * Created by liaojiacan on 2017/5/3.
 */
public class UsageTest {

    private final static Logger log = LoggerFactory.getLogger(UsageTest.class);

    private  MemcachedClient memcachedClient =null;

    @Before
    public void init() throws Exception {
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11211"));
        // 默认是采用余数哈希，可以修改为一致性哈希
        builder.setSessionLocator(new KetamaMemcachedSessionLocator());
        // 启用二进制协议，getAndTouch等方法仅在二进制协议中支持
        builder.setCommandFactory(new BinaryCommandFactory());
        memcachedClient= builder.build();
    }

    @Test
    public void testAdd() throws InterruptedException, MemcachedException, TimeoutException {
        /**
         * param1: key
         * param2: expire time
         * param3: data
         */
        memcachedClient.add("key1",0,"liaojiacan");
    }

    @Test
    public void testGet() throws InterruptedException, MemcachedException, TimeoutException {

        String val = memcachedClient.get("key1", new StringTranscoder());
        log.info("key:key1,value:{}",val);
    }


    @Test
    public void testSet() throws InterruptedException, MemcachedException, TimeoutException {
        /**
         * set = add + replace
         */
        memcachedClient.set("key1", 0,"liaojiacan-update");
    }

    @Test
    public void testReplace() throws InterruptedException, MemcachedException, TimeoutException {
         memcachedClient.replace("key1", 0,"liaojiacan-replace");
    }

    @Test
    public void testDelete() throws InterruptedException, MemcachedException, TimeoutException {
         memcachedClient.delete("key1");
    }





}
