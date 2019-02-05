package com.github.liaojiacan.leetcode.equal2Tree;

public class Solution {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }


    public boolean isSameTree(TreeNode p,TreeNode q){

        if(p==null && q==null)
            return true;
        if(p==null && q!=null)
            return false;
        if(p!=null && q==null)
            return false;
        if(q.val!=q.val)
            return  false;

        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}
