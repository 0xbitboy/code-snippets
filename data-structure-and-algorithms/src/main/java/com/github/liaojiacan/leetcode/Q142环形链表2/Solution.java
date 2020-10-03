package com.github.liaojiacan.leetcode.Q142环形链表2;

import com.github.liaojiacan.common.ListNode;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        // 快慢指针，如果最终能碰到一起则说明有环
        // 1. 设 head 到 环的起点的 距离为a
        // 2. 设 环的长度为b
        // 3. 设当slow和fast第一次相遇时，slow 走的距离为 a+x, 则 faster走的是2*(a+x)
        // 4. 当fast 和 slow相遇时，说明faster 至少走了1圈了，设fast 走了m圈
        // 5. fast 走的距离为 a + mb + x
        // 6. slow 走的距离为 a + x
        // 7. 因为 fast 走的速度是 slow的2倍，故，2* (a + x) = a + mb +x,  化简可得，a = mb -x,  x = mb -a;
        // 8. 假设 fast再走 a步，则 fast走的距离为 2a + mb + x, 由7可得， x=mb-a, 故，2a + mb + x = a + 2mb; 说明，fast刚好走了2m环，此时正是 环起点。
        // 9. 由8的性质，我们可以在快慢指针第一次相遇后，再设置一个新的指针再头节点开始 跟 fast 以1倍的速度走。 那么 当两个指针相遇时，说明新指针刚好走到了环的起点。

        if (head == null || head.next == null){
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while(fast != null){

            if(slow != fast){

                if(fast.next != null){
                    fast = fast.next.next;
                }else{
                    return null;
                }
                slow = slow.next;
            }else{
                // 第一次相遇的地方
                ListNode detcectP = head;

                while(detcectP != fast){
                    detcectP = detcectP.next;
                    fast = fast.next;
                }
                return detcectP;
            }

        }

        return null;

    }
}