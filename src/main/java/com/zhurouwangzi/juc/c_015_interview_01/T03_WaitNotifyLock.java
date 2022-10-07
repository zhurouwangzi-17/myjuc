package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.ArrayList;
import java.util.List;

/**
 * wait会释放锁，而notify不会释放锁
 * 以下代码的运行结果，t2并不是size为5时退出，而是等t1结束之后t2才退出，这是为什么？
 */
public class T03_WaitNotifyLock {

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        final Object lockObj = new Object();
        T03_WaitNotifyLock notifyHoldingLock = new T03_WaitNotifyLock();

        //t2线程
        new Thread(()->{
            synchronized (lockObj){
                if(notifyHoldingLock.size()!=5){
                    try {
                        lockObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end...");
            }
        },"t2").start();

        //t1线程
        new Thread(()->{
            synchronized (lockObj){
                for(int i =0;i<10;i++){
                    notifyHoldingLock.add(new Object());
                    System.out.println("t1 add "+i);
                    if(notifyHoldingLock.size()==5){
                        lockObj.notify();
                    }
                }
                System.out.println("t1 end...");
            }
        },"t1").start();
    }
}
