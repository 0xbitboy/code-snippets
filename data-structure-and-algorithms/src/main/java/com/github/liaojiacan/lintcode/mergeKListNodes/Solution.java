package com.github.liaojiacan.lintcode.mergeKListNodes;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

     public class ListNode {
         int val;
         ListNode next;
         ListNode(int val) {
             this.val = val;
              this.next = null;
         }
     }

     public ListNode mergeKLists(List<ListNode> listNodes){

         if(listNodes==null|| listNodes.size()==0){
             return null;
         }

         ListNode head = new ListNode(0);

         PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(listNodes.size(), new Comparator<ListNode>() {
             @Override
             public int compare(ListNode p, ListNode q) {
                 if(p.val==q.val)
                    return 0;
                 if(p.val>q.val)
                     return 1;
                 else
                     return -1;
             }
         });

         for(ListNode listNode : listNodes){
             priorityQueue.add(listNode);
         }


         ListNode p = head;
         while (priorityQueue.size()>0){
             ListNode tmp = priorityQueue.poll();
             p.next = tmp;
             if(tmp.next!=null){
                priorityQueue.add(tmp.next);
             }
             p = p.next;
         }

         return head.next;
     }
}
