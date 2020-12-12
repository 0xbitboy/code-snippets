package com.github.liaojiacan.acwing.Trie字符串统计853;

/**
 * @author liaojiacan
 * @date 2020/12/12
 * @desc
 */
import java.util.*;

public class Main{

    public static class Trie{

        class TrieNode{

            public TrieNode[] child = new TrieNode[26];
            public int cnt;
            public char value;

            public TrieNode(char c){
                this.value = c;
            }

        }


        TrieNode root = new TrieNode('#');

        public void insert(String str){

            TrieNode node = root;
            for(char c : str.toCharArray()){

                if(node.child[c-'a'] == null){
                    node.child[c-'a'] = new TrieNode(c);
                }

                node = node.child[c-'a'] ;

            }

            node.cnt ++;

        }

        public int queryCnt(String str){

            TrieNode node = root;

            for(char c : str.toCharArray()){

                if(node.child[c-'a'] == null){
                    return 0;
                }

                node = node.child[c-'a'] ;

            }

            return node.cnt;

        }

    }


    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Trie trie = new Trie();
        for(int i = 0; i< n; ++i){

            String command = scanner.next();
            String str = scanner.next();

            if("I".equals(command)){
                trie.insert(str);
            }

            if("Q".equals(command)){
                System.out.println(trie.queryCnt(str));
            }

        }

    }


}
