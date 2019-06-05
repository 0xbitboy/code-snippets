package com.github.liaojiacan.coding.实现LinkedList的add和remove方法;


/**
 * 单向链表的实现
 *
 * @author liaojiacan
 * @date 2019/6/5
 */
public class LinkedList<T> {

    private LinkedNode head;
    private LinkedNode tail;

    class LinkedNode {
        T value;
        LinkedNode next;

        public LinkedNode(T value, LinkedNode next) {
            this.value = value;
            this.next = next;
        }
    }


    public void add(T t) {
        if (head == null) {
            tail = head = new LinkedNode(t, null);
        } else {
            tail.next = new LinkedNode(t, null);
            tail = tail.next;
        }
    }

    public T remove(T t) {
        LinkedNode pre = null;
        LinkedNode cur = head;
        while (cur != null) {
            if (cur.value.equals(t)) {
                if (cur == head && cur == tail) {
                    head = tail = null;
                    return cur.value;
                }
                if (cur == head) {
                    head = cur.next;
                    return cur.value;
                }
                pre.next = cur.next;

                if (cur == tail) {
                    tail = pre;
                }
                return cur.value;
            }
            pre = cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedNode p = head;
        while (p != null) {
            sb.append(p.value).append(",");
            p = p.next;
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return "LinkedList{" + sb.toString() + "}";
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.remove(3);
        list.remove(2);
        list.remove(1);
        System.out.println(list.toString());

    }
}
