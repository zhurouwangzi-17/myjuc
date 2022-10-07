package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.ArrayList;
import java.util.List;

/**
 * t1线程notify之后必须调用wait方法释放锁，t2才能拿到锁并往下执行退出
 */
public class T04_WaitNotifyLock02 {

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        final Object lockObj = new Object();
        T04_WaitNotifyLock02 waitNotifyLock = new T04_WaitNotifyLock02();

        //t2线程
        new Thread(()->{
            synchronized (lockObj){
                if(waitNotifyLock.size()!=5){
                    try {
                        lockObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end...");
                //唤醒t1线程让t1线程继续执行
                lockObj.notify();
            }
        },"t2").start();

        //t1线程
        new Thread(()->{
            synchronized (lockObj){
                for(int i =0;i<10;i++){
                    waitNotifyLock.add(new Object());
                    System.out.println("t1 add "+i);
                    if(waitNotifyLock.size()==5){
                        //唤醒t2线程
                        lockObj.notify();
                        //释放t1线程的锁,让t2线程拿到锁才能够继续执行
                        try {
                            lockObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t1 end...");
            }
        },"t1").start();
    }
}
