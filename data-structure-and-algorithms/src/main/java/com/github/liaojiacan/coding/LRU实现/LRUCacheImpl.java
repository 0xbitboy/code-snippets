package com.github.liaojiacan.coding.LRU实现;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * 自己实现双向链表
 */
public class LRUCacheImpl<K, V> extends LRUCache<K, V> {


    static class DoubleLinkedList<K,V> {

        CacheNode<K,V> head;
        CacheNode<K,V> tail;
        int size;

        void addFirst(CacheNode<K,V> node) {
            if (head == null) {
                head = tail = node;
            }
            node.next = head;
            head.pre = node;
            head = node;
            tail.next = head;
            head.pre = tail;
            size ++ ;
        }

        void remove(CacheNode<K,V> node) {

            if(node == head && node == tail){
                size --;
                head = tail = null;
                return;
            }
            if(head == node){
                head = node.next;
            }
            if(tail == node){
                tail = tail.pre;
            }
            node.next.pre = node.pre;
            node.pre.next = node.next;

            size --;
        }

        CacheNode<K,V> removeLast() {
            if(size == 0){
                return null;
            }
            CacheNode<K,V> node = tail;
            remove(tail);
            return node;
        }

        public int size() {
            return size;
        }
    }

    static class CacheNode<K,V> {
        public K key;
        public V value;
        public CacheNode<K,V> pre;
        public CacheNode<K,V> next;

        public CacheNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<K, CacheNode<K,V>> cache;
    private DoubleLinkedList<K,V> link;
    private final int capacity;
    private V asNullReturn = null;
    public LRUCacheImpl(int capacity) {
        super(capacity);
        assert capacity > 0 : "capacity should > 0";
        this.capacity = capacity;
    }

    public LRUCacheImpl(int capacity, V asNullReturn){
        this(capacity);
        this.asNullReturn = asNullReturn;
    }

    @Override
    public V get(K key) {
        //每次的访问都要将节点移动到头节点
        CacheNode<K, V> node = this.cache.get(key);
        if(node == null){
            return asNullReturn;
        }
        link.addFirst(link.removeLast());
        return node.value;
    }

    @Override
    public void put(K key, V value) {
        if(link.size() == capacity){
            CacheNode<K,V> node = link.removeLast();
            cache.remove(node.key);
        }
        //插入头部
        CacheNode<K, V> node = new CacheNode<>(key, value);
        link.addFirst(node);
        cache.put(key,node);
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer,Integer> doubleLinkedList = new DoubleLinkedList<Integer,Integer>();

        for (int i = 0; i <1; i++) {
            doubleLinkedList.addFirst(new CacheNode<Integer,Integer>(i,i));
        }
        System.out.println(doubleLinkedList.size());
        while (doubleLinkedList.size() > 0){
            System.out.println(doubleLinkedList.removeLast().value);
        }
    }
}
