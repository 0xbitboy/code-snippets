package com.github.liaojiacan.leetcode.Q297二叉树的序列化与反序列化;

import com.github.liaojiacan.leetcode.common.TreeNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liaojiacan
 * @date 2019/2/21
 */
public class Solution {


	public static final String STR_NULL = "null";
	public static final String CHAR_BARACKET_LEFT = "[";
	public static final String CHAR_BARACKET_RIGHT = "]";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder stringBuilder  = new StringBuilder();
		stringBuilder.append(CHAR_BARACKET_LEFT);
		Deque<TreeNode> queue = new LinkedList<>();
		if(root!=null){
			queue.offer(root);
		}
		while (!queue.isEmpty()){
			TreeNode node = queue.poll();
			if(node == null){
				stringBuilder.append(STR_NULL).append(",");
				continue;
			}
			stringBuilder.append(node.val).append(",");
			queue.offer(node.left);
			queue.offer(node.right);
		}
		if(stringBuilder.length() > 1){
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
		}
		stringBuilder.append(CHAR_BARACKET_RIGHT);
		return stringBuilder.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if("[]".equals(data)){
			return null;
		}
		String[] values = data.substring(1,data.length()-1).split(",");
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		Deque<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		for (int i = 1; i < values.length ; i++) {
			TreeNode node = queue.poll();
			if(!STR_NULL.equals(values[i])){
				node.left =  new TreeNode(Integer.parseInt(values[i]));
				queue.offer(node.left);
			}
			if(i< values.length-1 && !STR_NULL.equals(values[i+1])){
				node.right = new TreeNode(Integer.parseInt(values[i+1]));
				queue.offer(node.right);
				i++;
			}
		}
		return root;
	}
	public static void main(String[] args) {
		Solution s = new Solution();
		TreeNode root = s.deserialize("[1,2,3,null,null,4,5]");
		System.out.println(s.serialize(root));
	}
}
