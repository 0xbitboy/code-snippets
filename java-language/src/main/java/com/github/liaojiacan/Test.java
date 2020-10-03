package com.github.liaojiacan;

/**
 * @author liaojiacan
 * @date 2020/2/3
 */
public class Test {


    public static void main(String[] args) {
        Object obj = new String();
        String className = obj.getClass().getName();
        System.out.println(className);
        try {
            Class<?> clazz = Class.forName(className);
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
