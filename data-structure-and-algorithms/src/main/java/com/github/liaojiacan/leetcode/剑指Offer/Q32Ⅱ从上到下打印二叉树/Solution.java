package com.github.liaojiacan.leetcode.剑指Offer.Q32Ⅱ从上到下打印二叉树;

//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
//
//
//
// 例如:
//给定二叉树: [3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其层次遍历结果：
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
//
//
//
//
// 提示：
//
//
// 节点总数 <= 1000
//
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-tra
//versal/
// Related Topics 树 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import com.github.liaojiacan.leetcode.common.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public static class LevelNode{
        TreeNode node;
        int level;

        public LevelNode(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<LevelNode> queue = new LinkedList<LevelNode>();
        queue.addLast(new LevelNode(root,1));
        while (!queue.isEmpty()){
            LevelNode levelNode = queue.poll();
            TreeNode node = levelNode.node;
            if(ans.size() != levelNode.level){
                ans.add(new ArrayList<>());
            }
            ans.get(ans.size()-1).add(node.val);
            if(node.left!=null)
                queue.addLast(new LevelNode(node.left,levelNode.level + 1));
            if(node.right!=null)
                queue.addLast(new LevelNode(node.right,levelNode.level + 1));
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
