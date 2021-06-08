package com.github.liaojiacan.coding.LRU实现;

import java.util.HashMap;
import java.util.Map;

/**
 * 最近使用过K次, 2Q改进
 * 用两个内部的LRU Map， 第一次访问 先存在第一个，如果第一个不为空，则优先淘汰第一个
 * 如果key存在在第一个Map，中代表被访问过2次了。移动到第二个去
 * @author liaojiacan
 * @date 2021/5/20
 * @desc
 */
public class LRUKCacheImpl<K,V> extends LRUCache<K,V> {

    public class LRUCacheNode{
        K key;
        V value;
        LRUCacheNode next;
        LRUCacheNode pre;

        public LRUCacheNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public class DoubleLinkedList{

        int size;
        LRUCacheNode dummyHead = new LRUCacheNode(null,null);
        LRUCacheNode dummyTail = new LRUCacheNode(null,null);

        public DoubleLinkedList() {
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
        }

        void addFirst(LRUCacheNode node){
            node.next = dummyHead.next;
            dummyHead.next = node;
            node.next.pre = node;
            node.pre = dummyHead;
            size ++;
        }

        void remove(LRUCacheNode node){
            if(size == 0){
                return;
            }
            LRUCacheNode next = node.next;
            next.pre = node.pre;
            node.pre.next = next;
            size --;
        }

        LRUCacheNode removeTail(){
            if(size == 0 ) return null;
            LRUCacheNode tail = dummyTail.pre;
            remove(tail);
            return tail;
        }

        int size(){
            return size;
        }
    }


    public class LRUMap{

        Map<K,LRUCacheNode> map = new HashMap<>();
        DoubleLinkedList dll = new DoubleLinkedList();

        void put(K key, V v){
            LRUCacheNode node = null;
            if(map.containsKey(key)){
                node = map.get(key);
                node.value = v;
                dll.remove(node);
            }
            dll.addFirst(node);
            map.put(node.key,node);

        }

        void addFirst(LRUCacheNode node){
            dll.addFirst(node);
            map.put(node.key,node);
        }

        LRUCacheNode get(K key){
            LRUCacheNode node = map.get(key);
            if(node == null){
                return null;
            }
            dll.remove(node);
            dll.addFirst(node);
            return node;
        }

        void removeOldest(){
            if(map.size() == 0){return;}
            LRUCacheNode tail = dll.removeTail();
            map.remove(tail.key);
        }

        void remove(LRUCacheNode node){
            dll.remove(node);
            map.remove(node.key);
        }

        boolean containsKey(K key){
            return map.containsKey(key);
        }

        int size(){
            return map.size();
        }
    }


    LRUMap firstLRUMap = new LRUMap();
    LRUMap secondLRUMap = new LRUMap();

    public LRUKCacheImpl(int capacity) {
        super(capacity);
    }

    @Override
    public V get(K key) {
        if(secondLRUMap.containsKey(key)){
            LRUCacheNode node = secondLRUMap.get(key);
            return  node==null  ? null : node.value;
        }else {
            LRUCacheNode node = firstLRUMap.get(key);
            if(node != null){
                firstLRUMap.remove(node);
                secondLRUMap.addFirst(node);
                return node.value;
            }
        }
        return  null;
    }

    @Override
    public void put(K key, V value) {
        if(firstLRUMap.containsKey(key)){
            LRUCacheNode node = firstLRUMap.get(key);
            firstLRUMap.remove(node);
            node.value = value;
            secondLRUMap.addFirst(node);
        }else if(secondLRUMap.containsKey(key)){
            secondLRUMap.put(key,value);
        }else if(capacity  <= firstLRUMap.size() + secondLRUMap.size()){
            if(firstLRUMap.size() > 0){
                firstLRUMap.removeOldest();
            }else {
                secondLRUMap.removeOldest();
            }
        }
    }
}
