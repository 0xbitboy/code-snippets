package com.github.liaojiacan.classinit;

/**
 *
 *  类加载
 *      先执行 夫类静态代码块，再执行子类静态代码快
 *
 *  new
 *      先执行 夫类构造函数，再执行子类构造函数。
 *  static A
    static B
    constructor A
    constructor B
 * Created by liaojiacan on 2017/5/10.
 */
public class B extends A{
    static {

        System.out.println("static B");

    }

    B(){
        System.out.println("constructor B");
    }

    public static void main(String[] args) {
        new B();

    }
}
