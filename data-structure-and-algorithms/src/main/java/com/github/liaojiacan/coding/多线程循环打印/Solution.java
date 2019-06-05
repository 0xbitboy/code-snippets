package com.github.liaojiacan.coding.多线程循环打印;

/**
 * 有三个线程ID分别是A、B、C,请用多线编程实现，在屏幕上循环打印10次ABCABC,请补充以下代码:
 *
 * @author liaojiacan
 * @date 2019/6/5
 */
public class Solution {
    public static void main(String[] args) {
        LetterPrinter maj = new LetterPrinter();
        Thread t_a = new Thread(new Thread_ABC(maj, 'A'));
        Thread t_b = new Thread(new Thread_ABC(maj, 'B'));
        Thread t_c = new Thread(new Thread_ABC(maj, 'C'));
        t_a.start();
        t_b.start();
        t_c.start();
    }
}

class LetterPrinter {
    //请补充代码

    private char letter = 'A';

    public void nextLetter() {
        switch (letter) {
            case 'A':
                letter = 'B';
                break;
            case 'B':
                letter = 'C';
                break;
            case 'C':
                letter = 'A';
                break;
        }
    }

    public char getLetter() {
        return letter;
    }

    public void printLetter() {
        System.out.println(letter);
    }
}

class Thread_ABC implements Runnable {
    //请补充代码
    private LetterPrinter letterPrinter;
    private char id;

    public Thread_ABC(LetterPrinter letterPrinter, char id) {
        this.letterPrinter = letterPrinter;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            synchronized (letterPrinter) {
                while (letterPrinter.getLetter() != id) {
                    try {
                        letterPrinter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                letterPrinter.printLetter();
                letterPrinter.nextLetter();
                letterPrinter.notifyAll();
            }
        }

    }
}