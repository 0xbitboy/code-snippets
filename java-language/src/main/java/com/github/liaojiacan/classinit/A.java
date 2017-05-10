package com.github.liaojiacan.classinit;

/**
 * Created by liaojiacan on 2017/5/10.
 */
public class A {
    static {

        System.out.println("static A");

    }
    A(){
        System.out.println("constructor A");
    }
}
