package com.github.liaojiacan.leetcode.Q101对称二叉树;

import com.github.liaojiacan.leetcode.common.TreeNode;

/**
 * @author liaojiacan
 * @date 2019/1/31
 */
class Solution {
	public boolean isSymmetric(TreeNode root) {
		if(root == null ){
			return true;
		}
		return  isSymmetric(root.left,root.right);
	}

	/**
	 * 对于2个数是否是对称的，只要满足根-> 左 -> 右 与 另一个树的 根-> 右 -> 左 的值一样即可。
	 * 也就是每个节点比较当前节点是否相同，再分2路分表比较4个子节点是否对称。
	 * @param left
	 * @param right
	 * @return
	 */

	private boolean isSymmetric(TreeNode left ,TreeNode right){
		if(left == null && right == null){
			return true;
		}
		if (left == null || right ==null){
			return false;
		}
		return left.val == right.val &&  isSymmetric(left.right,right.left) && isSymmetric(left.left,right.right);
	}
}