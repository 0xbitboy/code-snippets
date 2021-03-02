package com.github.liaojiacan.coding.LFU实现;

public abstract class LFUCache<K,V>{
    private int capacity;
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public abstract V get(K key);

    public abstract void put(K key, V value) ;



}
