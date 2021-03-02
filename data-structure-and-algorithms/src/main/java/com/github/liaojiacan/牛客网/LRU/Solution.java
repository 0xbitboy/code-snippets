package com.github.liaojiacan.牛客网.LRU;

import java.util.*;
import java.util.stream.*;

public class Solution {



    public static  class LRUCache<K,V>{

        public class Node<K,V>{
            public K key;
            public V value;
            public Node<K,V> pre;
            public Node<K,V> next;

            Node(K key ,V value){
                this.key = key;
                this.value = value;
            }

        }

        public class DoubleLinkedList<K,V>{

            public Node<K,V> dummyHead;
            public Node<K,V> dummyTail;
            public int size;

            DoubleLinkedList(){
                this.size = 0;
                dummyHead  = new Node<K,V>(null,null);
                dummyTail = new Node<K,V>(null,null);
                dummyTail.pre = dummyHead;
                dummyHead.next = dummyTail;
            }

            public void addFirst(Node<K,V> node){
                node.next = dummyHead.next;
                dummyHead.next = node;
                node.pre = dummyHead;
                node.next.pre = node;
                this.size ++ ;
            }

            public Node<K,V> removeNode(Node<K,V> node){
                if(this.size == 0){
                    return null;
                }
                Node pre = node.pre;
                Node next = node.next;
                pre.next = next;
                next.pre = pre;
                this.size--;
                return node;
            }

            public Node<K,V> removeTail(){
                return this.removeNode(dummyTail.pre);
            }

        }

        private Map<K,Node<K,V>> cache;
        private DoubleLinkedList<K,V> dll;
        private int capacity;
        public LRUCache(int capacity){
            cache = new HashMap<K,Node<K,V>>(capacity);
            dll = new DoubleLinkedList();
            this.capacity = capacity;
        }

        public void set(K key , V value){
            // 如果key存在则更新value，并将节点移动到头节点
            Node<K,V> node = cache.get(key);
            if(node != null){
                node.value = value;
                dll.removeNode(node);
                dll.addFirst(node);
            }else {
                if(cache.size() >= capacity){
                    Node<K,V> tail = dll.removeTail();
                    if(tail != null){
                        cache.remove(tail.key);
                    }
                }
                node = new Node<K,V>(key,value);
                dll.addFirst(node);
                cache.put(key,node);
            }
        }

        public V get(K key){
            Node<K,V> node= cache.get(key);
            if(node != null){
                dll.removeNode(node);
                dll.addFirst(node);
            }
            return node == null?  null:  node.value;
        }

    }

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        LRUCache<Integer,Integer> cache = new LRUCache<Integer,Integer>(k);

        List<Integer> ans = new ArrayList<>();

        for(int[] op : operators){
            int opBit = op[0];
            switch(opBit){
                case 1:
                    cache.set(op[1],op[2]);
                    break;
                case  2:
                    Integer value = cache.get(op[1]);
                    ans.add(value == null ? -1 : value);
                default:
                    break;
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();

    }

    public static void main(String[] args) {
        int[][] ops = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        int k = 3;
        System.out.println(Arrays.toString(new Solution().LRU(ops,k)));
    }
}