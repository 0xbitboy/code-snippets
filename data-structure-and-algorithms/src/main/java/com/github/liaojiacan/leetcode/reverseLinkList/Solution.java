package com.github.liaojiacan.leetcode.reverseLinkList;

public class Solution {


    public class ListNode{
        int val;
        ListNode next;
    }


    public ListNode reverse(ListNode head){

        if(head==null || head.next==null) return  null;

        ListNode p2 = head.next;
        ListNode p3 = p2.next;
        head.next=null;
        while (p2!=null){

            p2.next=head;
            head = p2;
            p2 = p3;
            if(p3!=null){
                p3 = p3.next;
            }

        }


        return  head;


    }


}
