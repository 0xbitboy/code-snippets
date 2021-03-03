package com.github.liaojiacan.acwing.基础算法.堆排序;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author liaojiacan
 * @date 2020/12/22
 * @desc
 */
public class Main{

    static class BigHeap{

        public int[] data;
        public int size;
        public BigHeap(int capacity){
            this.size = 0;
            data = new int[capacity+1];
        }
        public int left(int i){return i << 1;}
        public int right(int i){return (i << 1) + 1;}
        public int parent(int i){return i >> 1;}

        void up(int i){
            int p = parent(i);
            if(p > 0 && data[p] < data[i]){
                swap(p,i);
                up(p);
            }
        }

        void down(int i){
            int t = i;
            if(left(i) <= size && data[left(i)] > data[i]) t = left(i);
            if(right(i) <= size && data[right(i)] > data[t]) t = right(i);
            if( t != i){
                swap(t,i);
                down(t);
            }
        }

        void insert(int value){
            assert data.length+1 < size;
            data[++size] = value;
            up(size);
        }


        void swap(int i,int j){
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        int top(){
            return data[1];
        }

        int removeTop(){
            int res = data[1];
            data[1] = data[size--];
            down(1);
            return res;
        }

        boolean isEmpty(){
            return size == 0;
        }
    }


    public static void main(String[] args) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wirter = new BufferedWriter(new OutputStreamWriter(System.out));
        String s[] = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        s = reader.readLine().split(" ");
        BigHeap bigHeap = new BigHeap(m);
        for(int i = 0 ;i<n ; ++i){
            int v = Integer.parseInt(s[i]);
            if(bigHeap.size < m){
                bigHeap.insert(v);
            }else if(v < bigHeap.top()){
                bigHeap.removeTop();
                bigHeap.insert(v);
            }
        }

        int[] nums = new int[m];

        for (int i = m-1; i >= 0; i--) {
            nums[i] = bigHeap.removeTop();
        }

        for (int i = 0; i < m; i++) {
            wirter.write(nums[i]+" ");
        }

        wirter.flush();
        wirter.close();
        reader.close();
    }
}