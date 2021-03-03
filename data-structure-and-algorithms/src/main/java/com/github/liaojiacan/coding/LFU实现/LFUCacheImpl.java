package com.github.liaojiacan.coding.LFU实现;

import java.util.HashMap;
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

    private class DoubleLinkedList{

        Node<K,V> dummyHead;
        Node<K,V> dummyTail;
        int size;

        public DoubleLinkedList() {
            dummyHead = new Node<>(null,null);
            dummyTail = new Node<>(null,null);
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
        }

        public void addLast(Node<K,V> node){
            Node<K, V> last = dummyTail.pre;
            last.next = node;
            node.pre = last;
            node.next = dummyTail;
            size++;
        }

        public void addFirst(Node<K,V> node){
            Node<K, V> head = dummyHead.next;
            head.pre = node;
            node.next = head;
            node.pre = dummyHead;
            size++;
        }


        public Node<K,V> getFirst(){
            if(size == 0){
                return null;
            }
            return dummyHead.next;
        }
        public Node<K,V> getLast(){
            if(size == 0){
                return null;
            }
            return  dummyTail.pre;
        }

        public void remove(Node<K,V> node){

            if(size == 0){
                return;
            }
            Node<K, V> pre = node.pre;
            Node<K, V> next = node.next;
            pre.next = next;
            next.pre = pre;
            node.next = null;
            node.pre = null;
            size--;
        }

        public int size(){
            return size;
        }
    }

    /**
     * 存放访问评率的缓存节点
     * 1 -> A -> B -> C
     * 2 -> D
     * 3 -> G
     * 4
     */
    private Map<Integer,DoubleLinkedList> fMap;
    private Map<K,Node<K,V>> cacheMap;
    private int maxFreq;
    public LFUCacheImpl(int capacity) {
        super(capacity);
        fMap = new HashMap<>();
        cacheMap = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if(!cacheMap.containsKey(key)){
            return null;
        }

        Node<K, V> node = cacheMap.get(key);
        DoubleLinkedList dll = fMap.get(node.frequency);
        if (dll != null) {
            dll.remove(node);
        }
        node.frequency++;
        dll = fMap.getOrDefault(node.frequency,new DoubleLinkedList());
        fMap.put(node.frequency,dll);
        dll.addFirst(node);
        maxFreq = Math.max(maxFreq,node.frequency);
        return node.val;
    }

    @Override
    public void put(K key, V value) {
        if(cacheMap.containsKey(key)){
            Node<K, V> node = cacheMap.get(key);
            node.val = value;
            get(key);
            return;
        }

        if(this.cacheMap.size() == this.capacity){
            for (int i = 1; i <= maxFreq; i++) {
                DoubleLinkedList dll = fMap.get(i);
                if(dll!=null && dll.size()>0){
                    Node<K,V> node = dll.getLast();
                    dll.remove(node);
                    cacheMap.remove(node.key);
                    break;
                }
            }
        }

        Node<K,V> node = new Node<>(key,value);
        node.frequency = 0;
        cacheMap.put(key,node);
        get(key);
    }

    public static void main(String[] args) {

        LFUCache<Integer, Integer> cache = new LFUCacheImpl<Integer, Integer>(2);

        cache.put(1,1);
        System.out.println(cache.get(1));
        cache.put(2,2);
        cache.put(3,3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));

    }
}
