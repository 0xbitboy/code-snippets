package com.github.liaojiacan.acwing.数据结构.合并集合836;

import java.util.*;

/**
 * 并查集
 * 1. 快速合并两个集合
 * 2. 查找一个节点ID
 * 3. 判断2个节点是否是在同一个集合中
 * @see https://www.acwing.com/problem/content/838/
 * @author liaojiacan
 * @date 2020/12/12
 * @desc
 */
public class Main {


    public static class UnionFindSet{

        Map<Integer,Integer> map = new HashMap<>();

        public void merge(int setIdA, int setIdB){
            map.put(findSetId(setIdA),findSetId(setIdB));
        }

        public int findSetId(int value){
            if(map.get(value) == null ){
                map.put(value,value);
                return value;
            }
            if (map.get(value) != value){
                map.put(value,findSetId(map.get(value)));
            }
            return map.get(value);
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UnionFindSet unionFindSet = new UnionFindSet();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for (int i = 0; i < m; i++) {
            String ops = scanner.next();
            int setIdA = scanner.nextInt();
            int setIdB = scanner.nextInt();

            if("M".equals(ops)){
                unionFindSet.merge(setIdA,setIdB);
            }else if("Q".equals(ops)){
                if(unionFindSet.findSetId(setIdA) == unionFindSet.findSetId(setIdB)){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }
        }


    }
}
