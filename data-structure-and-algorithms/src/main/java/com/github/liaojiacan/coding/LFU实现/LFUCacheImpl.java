package com.github.liaojiacan.coding.LFU实现;

import java.util.Map;

/**
 * S: 缓存 get 和 set接口
 * N：非并发环境
 * A：O（1），双向链表，每次使用后调整位置。
 * K：任意对象
 * E：
 * 如何使用双向链表和hash表来维护，缓存对象的访问频率的问题
 * @author liaojiacan
 * @date 2021/2/17
 * @desc
 */
public class LFUCacheImpl<K,V> extends LFUCache<K,V>{


    private class Node<K,V>{
        K key;
        V val;
        Node<K,V> pre;
        Node<K,V> next;
        int frequency;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private class DoubleLinkedList<T>{

        public void addLast(T node){

        }

        public T getFirst(){
            return null;
        }

        public void remove(T node){

        }
    }

    /**
     * 存放访问评率的缓存节点
     * 1 -> A -> B -> C
     * 2 -> D
     * 3 -> G
     * 4
     */
    private Map<Integer,DoubleLinkedList<Node<K,V>>> fMap;
    private Map<K,Node<K,V>> cacheMap;

    public LFUCacheImpl(int capacity) {
        super(capacity);
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {
        if(cacheMap.containsKey(key)){
            Node<K, V> node = cacheMap.get(key);
            node.val = value;
            node.frequency = 1;
        }
    }
}
