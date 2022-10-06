package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * semaphore：信号灯
 * 当Semaphore的构造函数参数传1的时候表示同一时刻只有1个线程执行
 * acquire()方法表示从容器中去拿一个信号，如果拿到了就往下执行，拿不到就阻塞等待
 * 如果构造函数设置为2，那么2个线程就能同时拿到信号就能同时执行
 * 所以构造函数的参数设置的数量越大表示能够同时执行的线程越多
 */
public class T09_TestSemaphore {
    public static void main(String[] args) {
        //信号灯  构造函数参数：允许*个线程同时执行
        Semaphore semaphore = new Semaphore(1);

        //开启T1线程
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T1 running...");
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //将信号归还到容器中
                semaphore.release();
            }
        },"T1").start();

        //开启T2线程
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T2 running...");
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //将信号归还到容器中
                semaphore.release();
            }
        },"T2").start();

    }
}
