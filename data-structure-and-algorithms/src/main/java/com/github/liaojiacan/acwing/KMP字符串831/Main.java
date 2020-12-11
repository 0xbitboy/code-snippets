package com.github.liaojiacan.acwing.KMP字符串831;

/**
 * @author liaojiacan
 * @date 2020/12/10
 * @desc
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(reader.readLine());
        String p = reader.readLine();
        int n = Integer.parseInt(reader.readLine());
        String s = reader.readLine();

        int[] next = new int[m];
        computeNextTable(p,next);

        int k = -1;

        for(int i= 0 ; i< n; i++){

            while( k >=0 && p.charAt(k+1) != s.charAt(i)){
                k = next[k];
            }

            if(p.charAt(k+1) == s.charAt(i)){
                k++;
            }

            if(k == m-1){
                writer.write(i-m + 1 + " ");
                k = next[k];
            }

        }

        writer.flush();
        writer.close();
        reader.close();
    }


    private static void computeNextTable(String p, int[] next){
        int m = p.length();

        next[0] = -1;
        int k = -1;

        for(int i = 1; i < m ; ++i){

            while(k >= 0 && p.charAt(k + 1) != p.charAt(i)){
                k = next[k];
            }

            if(p.charAt(k+1) == p.charAt(i) ){
                k++;
            }

            next[i] = k;

        }


    }
}