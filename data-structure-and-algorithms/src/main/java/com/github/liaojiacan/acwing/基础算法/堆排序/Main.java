package com.github.liaojiacan.acwing.基础算法.堆排序;

/**
 * @author liaojiacan
 * @date 2020/12/22
 * @desc
 */
import java.util.*;
import java.io.*;
public class Main{

    static class Heap{

        public int[] data;
        public int size;
        public Heap(int capcity){
            this.size = 0;
            data = new int[capcity+1];
        }

        void up(int idx){
            int p = idx / 2;
            if(data[p] < data[idx]){
                swap(p, idx);
                up(p);
            }

        }

        void down(int idx){
            int t = idx;
            if(idx* 2 < size && data[idx*2] > data[idx]) t = idx*2;
            if(idx*2 + 1 < size && data[idx*2+1] > data[idx]) t= idx*2+1;
            if(t != idx){
                swap(t, idx);
                down(t);
            }
        }

        void insert(int value){
            assert data.length+1 < size;
            data[++size] = value;
            up(size);
        }

        void delete(int idx){
            data[idx] = data[size--];
            down(idx);
            up(idx);
        }



        void swap(int i,int j){
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        int top(){
            return data[1];
        }

        void removeTop(){
            delete(1);
        }

    }


    public static void main(String[] args) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wirter = new BufferedWriter(new OutputStreamWriter(System.out));
        String s[] = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        s = reader.readLine().split(" ");
        Heap heap = new Heap(m);
        for(int i = 0 ;i<n ; ++i){
            int v = Integer.parseInt(s[i]);
            if(heap.size <= m){
                heap.insert(v);
            }else if(v < heap.top()){
                heap.removeTop();
                heap.insert(v);
            }
        }

        for(int i = m ; i > 0 ; i--){
            System.out.print(heap.data[i] + " ");
        }

    }
}