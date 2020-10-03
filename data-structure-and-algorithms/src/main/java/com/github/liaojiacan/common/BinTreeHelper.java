package com.github.liaojiacan.common;

import com.github.liaojiacan.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author liaojiacan
 * @date 2019/6/24
 */
public class BinTreeHelper {

    /**
     * Encodes a tree to a single string.
     */
    public static  String serialize(TreeNode root) {
        if(root == null ){
            return "[]";
        }
        List<Integer> values = new ArrayList<>(1024);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            values.add(node==null? null: node.getVal());
            if(node!=null){
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
        }
        // 去除掉最后的"假"叶子节点
        while (values.get(values.size()-1) == null ){
            values.remove(values.size()-1);
        }

        StringBuilder sb = new StringBuilder(1024);
        sb.append('[');
        values.forEach(value->{
            sb.append(value).append(",");
        });
        sb.append(']');
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * Decodes your encoded data to tree.
     */
    public static  TreeNode deserialize(String data) {
        if(data == null || "".equals(data) || "[]".equals(data)){
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
                node.setLeft(left);
                queue.add(left);
            }
            value = values[++i].trim();

            if(!"null".equals(value)){
                TreeNode right = new TreeNode(Integer.parseInt(value));
                node.setRight(right);
                queue.add(right);
            }
        }
        return root;
    }

}
