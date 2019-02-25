package com.github.liaojiacan.leetcode.Q297二叉树的序列化与反序列化;

import com.github.liaojiacan.leetcode.common.TreeNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liaojiacan
 * @date 2019/2/21
 */
public class Solution {


	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		StringBuilder sb = new StringBuilder(1024);
		sb.append('[');
		while(!queue.isEmpty()){
			TreeNode head = queue.poll();
			if(head == null ){
				if(sb.length()>1)
					sb.append(",null");
				continue;
			}
			if(sb.length()==1){
				sb.append(head.val);
			}else{
				sb.append(",").append(head.val);
			}
			queue.add(head.left);
			queue.add(head.right);
		}
		sb.append(']');
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if(data == null || "".equals(data)){
			return null;
		}
		String[] values = data.trim().substring(1, data.length() - 1).split(",");
		if(values.length == 1 && "".equals(values[0])){
			return null;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.parseInt(values[0].trim()));
		queue.add(root);
		for(int i=1 ; i<values.length ;i++){
			TreeNode node = queue.poll();
			String value = values[i].trim();
			if(!"null".equals(value)){
				TreeNode left = new TreeNode(Integer.parseInt(value));
				node.left = left;
				queue.add(left);
			}
			value = values[++i].trim();

			if(!"null".equals(value)){
				TreeNode right = new TreeNode(Integer.parseInt(value));
				node.right = right;
				queue.add(right);
			}
		}
		return root;
	}
	public static void main(String[] args) {
		Solution s = new Solution();
		TreeNode root = s.deserialize("[]");
		System.out.println(s.serialize(root));
	}
}
