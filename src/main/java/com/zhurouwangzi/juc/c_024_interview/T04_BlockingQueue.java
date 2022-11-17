package com.zhurouwangzi.juc.c_024_interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 这里使用两个阻塞队列来实现
 * 以下代码是粘过来的msb的代码
 * 阻塞队列实现的关键点是用两个容量都为1的阻塞队列，然后使用他们的put和take这两个阻塞方法来实现
 */
public class T04_BlockingQueue {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for(char c : aI) {
                System.out.print(c);
                try {
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {
            for(char c : aC) {
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t2").start();
    }
}
