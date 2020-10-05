package com.github.liaojiacan.coding.LRU实现;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用LinkedHashMap的模版功能实现
 * @param <K>
 * @param <V>
 */
public class LRUCacheLinkedHashMapImpl<K,V> extends LRUCache<K,V> {

    Map<K,V> cache;

    public LRUCacheLinkedHashMapImpl(int capacity) {
        super(capacity);
        cache = new LinkedHashMap<K,V>(capacity,0.75F,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return this.size() > capacity;
            }
        };
    }

    @Override
    public V get(K key) {
        return cache.getOrDefault(key,null);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key,value);
    }
}
