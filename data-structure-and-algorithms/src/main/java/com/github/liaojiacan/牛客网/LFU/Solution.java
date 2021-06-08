package com.github.liaojiacan.牛客网.LFU;

import java.util.*;

/**
 * @author liaojiacan
 * @date 2021/4/1
 * @desc
 */
public class Solution {

    public class Node{
        int key;
        int val;
        Node pre;
        Node next;
        int freq;

        public Node(int key , int val){
            this.key = key;
            this.val = val;
        }
    }
    public class DoubleLinkedList{

        private Node dummyHead;
        private Node dummyTail;
        private int size;

        public DoubleLinkedList(){
            this.dummyHead = new Node(0,0);
            this.dummyTail =  new Node(0,0);
            this.dummyHead.next = this.dummyTail;
            this.dummyTail.pre = this.dummyHead;
        }

        public void addFirst(Node node){
            node.next = this.dummyHead.next;
            this.dummyHead.next = node;
            node.pre = this.dummyHead;
            node.next.pre = node;
            this.size ++;
        }

        public void addLast(Node node){
            node.pre = this.dummyTail.pre;
            node.pre.next = node;
            node.next = this.dummyTail;
            this.dummyTail.pre = node;
            this.size ++;
        }

        public void removeNode(Node node){
            Node next = node.next;
            node.pre.next = next;
            next.pre = node.pre;
            node.next = null;
            node.pre = null;
            this.size --;
        }

        public Node removeFirst(){
            assert size > 0;
            Node head = dummyHead.next;
            removeNode(head);
            return head;
        }

        public Node removeLast(){
            assert size > 0;
            Node tail  = dummyTail.pre;
            removeNode(tail);
            return tail;
        }

        public  Node getHead(){
            return dummyHead.next;
        }

        public Node getTail(){
            return dummyTail.pre;
        }

        public int size(){
            return size;
        }

    }

    private Map<Integer,Node> cache = new HashMap<>();
    private Map<Integer,DoubleLinkedList> freqMap =  new HashMap<>();
    private int capacity = 0;
    private int maxFreq = 0;

    private void set(int key, int val){
        Node node = cache.get(key);
        if(node != null){
            node.val = val;
        }else{
            // 已经达到最大
            if(this.capacity == cache.size()){
                for(int i= 0 ; i <= maxFreq ; ++i){
                    DoubleLinkedList dll = freqMap.get(i);
                    if(dll != null && dll.size() > 0 ){
                        Node tail = dll.removeLast();
                        cache.remove(tail.key);
                        break;
                    }
                }
            }
            node = new Node(key,val);
            cache.put(key,node);
        }
        get(key);
    }

    private int get(int key){
        Node node = cache.get(key);
        if(node == null){
            return -1;
        }
        int freq = node.freq;
        DoubleLinkedList dll = freqMap.get(freq);
        if(dll != null){
            dll.removeNode(node);
        }
        node.freq+=1;
        DoubleLinkedList nextFreqDll = freqMap.getOrDefault(node.freq , new DoubleLinkedList());
        nextFreqDll.addFirst(node);
        freqMap.put(node.freq,nextFreqDll);
        maxFreq = Math.max(node.freq, maxFreq);
        return node.val;
    }

    /**
     * lfu design
     * @param operators int整型二维数组 ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LFU (int[][] operators, int k) {
        // write code here
        this.capacity = k;
        assert this.capacity > 0;
        List<Integer> ans = new ArrayList<>();
        for(int[] op: operators){
            switch (op[0]){
                case 1:
                    set(op[1],op[2]);
                    break;
                case 2:
                    ans.add(get(op[1]));
                    break;
                default:
                    throw new RuntimeException("not supported");

            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();

    }

    public static void main(String[] args) {
        int[][] in = {{1,1,1},{1,2,2},{1,3,2},{1,2,4},{1,3,5},{2,2},{1,4,4},{2,1}};
        System.out.println(Arrays.toString(new Solution().LFU(in,3)));
    }
}