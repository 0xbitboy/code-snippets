package com.github.liaojiacan.coding.LRU实现;

public abstract class LRUCache<K,V>{

    public LRUCache(int capacity) {

    }

    public abstract V get(K key);

    public abstract void put(K key, V value) ;



}
