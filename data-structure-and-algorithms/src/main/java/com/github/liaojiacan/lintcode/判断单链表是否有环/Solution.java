package com.github.liaojiacan.lintcode.判断单链表是否有环;

public class Solution {

    public class ListNode{
        int v;
        ListNode next;
    }


    /**
     * 假设链表有环，则会相遇到
     * 设slow走了 s步，则fast 走了2s步，而且fast走的路为slow走的加上n个环的步数 2s=s+n*r -> s=n*r
     * @param head
     * @return
     */
    public boolean isLoop(ListNode head){

        ListNode slow = head;
        ListNode fast = head;

        while (slow.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                break;
            }
        }

        if(fast==null || fast.next==null){
            return false;
        }

        return true;

    }

}
