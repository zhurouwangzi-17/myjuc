package com.zhurouwangzi.juc.c_024_interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这段代码使用原子类AtomicInteger来实现，代码从msb粘贴过来的
 * 使用AtomicInteger实现的关键点在于它是原子性的操作，操作它的值是有锁的,它底层是用Unsafe这个类来实现的
 */
public class T06_AtomicInteger {
    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();


        new Thread(() -> {

            for (char c : aI) {
                while (threadNo.get() != 1) {}
                System.out.print(c);
                threadNo.set(2);
            }

        }, "t1").start();

        new Thread(() -> {

            for (char c : aC) {
                while (threadNo.get() != 2) {}
                System.out.print(c);
                threadNo.set(1);
            }
        }, "t2").start();
    }
}
