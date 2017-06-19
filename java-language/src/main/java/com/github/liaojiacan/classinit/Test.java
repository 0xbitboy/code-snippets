package com.github.liaojiacan.classinit;

/**
 * Created by liaojiacan on 2017/6/19.
 */

class Meal {
    Meal() { System.out.println("Meal()"); }
}
class Bread {
    Bread() { System.out.println("Bread()"); }
}
class Cheese {
    Cheese() { System.out.println("Cheese()"); }
}
class Lettuce {
    Lettuce() { System.out.println("Lettuce()"); }
}
class Lunch extends Meal {
    Lunch() { System.out.println("Lunch()");}
}
class PortableLunch extends Lunch {
    Bread b = new Bread();
    PortableLunch() {
        System.out.println("PortableLunch()");
    }
}
class Sandwich extends PortableLunch {
    Bread b = new Bread();
    Cheese c = new Cheese();
    Lettuce l = new Lettuce();
    Sandwich() {
        System.out.println("Sandwich()");
    }

}

public class Test {
    public static void main(String[] args) {

        /**
         * 1.成员变量初始化优先于构造器执行。。
         * 2.父类的初始化优先执行
         */
        new Sandwich();
    }

}
