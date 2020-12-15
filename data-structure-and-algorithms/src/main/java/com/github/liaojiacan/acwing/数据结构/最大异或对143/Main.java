package com.github.liaojiacan.acwing.数据结构.最大异或对143;

import java.util.Scanner;

/**
 * @author liaojiacan
 * @date 2020/12/12
 * @desc
 */
public class Main {

    private static class Tire{

        class TireNode{
            public TireNode[] child;
            public int cnt;
            public int nodeValue;

            public TireNode(TireNode[] child, int cnt, int nodeValue) {
                this.child = child;
                this.cnt = cnt;
                this.nodeValue = nodeValue;
            }
        }

        TireNode root = new TireNode(new TireNode[2], 0, 0);
        public void insert(int value){
            TireNode node = root;
            // 从数值的高位到地位
            for (int i = 30; i >=0 ; i--) {
                // 判断该位是0还是1
                int digist =(value >> i) & 1;
                if(node.child[digist] == null){
                    node.child[digist] = new TireNode(new TireNode[2],0,digist);
                }
                node = node.child[digist];
            }
            node.cnt++;
        }

        public int findMaximumXOR(int num){
            int ans = 0;
            TireNode node = root;
            for (int i = 30; i >=0 ; i--) {
                // 取出当前的第位，在trie树中取相反的节点
                int digist = num>>i & 1;
                int target = digist == 1 ? 0 : 1 ;
                if(node.child[target] != null){
                    node = node.child[target];
                    ans = ((ans << 1) +(target));
                }else if(node.child[digist] !=null){
                    node = node.child[digist];
                    ans = ((ans << 1) +(digist));
                }
            }
            return ans ^ num;
        }

    }




    public static int findMaximumXOR(int[] nums) {
        int ans = 0;
        Tire tire = new Tire();
        for (int i = 0; i < nums.length; i++) {
            tire.insert(nums[i]);
            ans = Math.max(ans,tire.findMaximumXOR(nums[i]));
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int ans = findMaximumXOR(nums);
        System.out.println(ans);
    }


}
