package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryInterruptibly可以对线程interrrupt方法做出相应
 * 在一个线程等待锁的过程中，可以被打断
 */
public class T03_ReentrantLock3 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        //线程1
        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start...");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (InterruptedException ex){
                System.out.println("t1 interruptibly...");
            }finally {
                System.out.println("t1 end...");
                lock.unlock();
            }
        });
        t1.start();

        //线程2
        Thread t2 = new Thread(()->{
            try {
//                lock.lock();//这里直接使用lock加锁的话并不会进入到InterruptedException异常中
                lock.lockInterruptibly();
                System.out.println("t2 start...");
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException ex){
                System.out.println("t2 interruptibly...");
            }finally {
                System.out.println("t2 end...");
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
}
