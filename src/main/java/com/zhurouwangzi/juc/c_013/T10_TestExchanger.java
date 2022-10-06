package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.Exchanger;

/**
 * 2个线程通信，交换数据
 * 2个以上的线程不行
 */
public class T10_TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        //T1线程
        new Thread(()->{
            String s = "s1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"T1").start();

        //T2线程
        new Thread(()->{
            String s = "s2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"T2").start();
    }
}
