package com.github.liaojiacan.leetcode.剑指Offer.Q32从上到下打印二叉树;

import com.github.liaojiacan.leetcode.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liaojiacan
 * @date 2020/11/5
 * @desc
 */
class Solution {
    public int[] levelOrder(TreeNode root) {
        //层次遍历
        Deque<TreeNode> queue = new LinkedList<>();
        int[] ans = new int[1001];
        int k = 0;
        queue.addLast(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.pop();
            if(node.left != null){
                queue.addLast(node.left);
            }
            if(node.right !=null ){
                queue.addLast(node.right);
            }
            ans[k++] = node.val;
        }
        return ans;
    }
}
