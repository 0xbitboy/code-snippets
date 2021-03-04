package com.github.liaojiacan.coding.跳表的实现;

import java.util.Random;

/**
 * 按一定的概率上升跳表
 *
 * @author liaojiacan
 * @date 2021/3/3
 * @desc
 */
public class SkipList<K extends Comparable,V> {

    private class SkipListNode<K,V>{
        K key;
        V val;
        SkipListNode pre,next,up,down;

        public SkipListNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private SkipListNode<K,V> head;
    private SkipListNode<K,V> tail;
    private int level;
    private Random random;
    private double probability;
    private int size;


    public SkipList(double probability) {
        this.probability = probability;
        this.level = 0;
        this.random = new Random();
        this.head = new SkipListNode<>(null,null);
        this.tail = new SkipListNode<>(null,null);
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public V get(K key){
        SkipListNode<K, V> node = findNode(key);
        if(key.equals(node.key)){
            return node.val;
        }
        return null;
    }

    public void put(K key, V val){
        // 查找一个节点，等于key或者最接近key的左值。
        SkipListNode<K,V> node = findNode(key);
        assert node != null;
        if(key.equals(node.key)){
            node.val = val;
            return;
        }
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(key, val);
        // 插入一个节点
        insertNode(node,newNode);
        // 随机一定的概率是否要上浮
        int currentLevel = 0;
        while (random.nextDouble() <= this.probability){
            if(currentLevel >= level){
                addEmptyLevel();
            }
            // 向左边找一个有up的节点，然后在这个up节点插入一个当前节点的拷贝
            while (node.up == null){
                node = node.pre;
            }
            node = node.up;

            SkipListNode<K, V> up = new SkipListNode<>(key, val);
            up.down = newNode;
            up.next = node.next;
            up.next.pre = up;
            up.pre = node;
            newNode = up;
            currentLevel++;
        }

        size++;
    }

    private void addEmptyLevel() {
        SkipListNode<K,V> h = new SkipListNode<>(null,null);
        SkipListNode<K,V> t = new SkipListNode<>(null,null);
        h.next = t;
        t.pre = h;
        h.down = head;
        head.up = h;
        t.down = tail;
        tail.up = t;
        head = h;
        tail = t;
        level++;
    }

    private void insertNode(SkipListNode preNode, SkipListNode node){
        SkipListNode next = preNode.next;
        preNode.next = node;
        node.next = next;
        node.pre = preNode;
    }



    private SkipListNode<K, V> findNode(K key) {
        SkipListNode<K,V> h = this.head;
        while (true){
            if(h.next !=null && (h.next.key != null && ((Comparable)h.next.key).compareTo(key) <= 0)){
               h = h.next;
            }
            if(h.down != null){
                h = h.down;
            }else if(h.next!=null && (h.next.key == null || ((Comparable)h.next.key).compareTo(key) > 0)){
                break;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        SkipList<Integer, String> skipList = new SkipList<Integer, String>(0.5d);
        skipList.put(1,"A");
        skipList.put(3,"C");
        skipList.put(2,"B");
        skipList.put(4,"D");
        System.out.println(skipList.get(4));


    }

}
