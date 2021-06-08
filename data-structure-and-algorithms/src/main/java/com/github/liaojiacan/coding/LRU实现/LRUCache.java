package com.github.liaojiacan.coding.LRU实现;

public abstract class LRUCache<K,V>{


    int capacity;
    public LRUCache(int capacity) {
        capacity = capacity;
    }

    public abstract V get(K key);

    public abstract void put(K key, V value) ;



}
