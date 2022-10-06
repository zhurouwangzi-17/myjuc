package com.zhurouwangzi.juc.c_012_00_AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 此代码模拟使用AtomicInteger对变量进行原子性的自增
 */
public class T02_AtomicInteger {
    AtomicInteger count = new AtomicInteger();

    void m(){
        for(int i =0;i<10000;i++){
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        //1.new出来对象
        T02_AtomicInteger atomicInteger = new T02_AtomicInteger();
        //2.开启10个线程
        List<Thread> threadList = new ArrayList<>();
        for(int i =0;i<10;i++){
            threadList.add(new Thread(atomicInteger::m,"thread"+i));
        }
        //3.让10个线程都开始工作
        threadList.forEach((t)->t.start());
        //4.等待10个线程都执行完毕
        threadList.forEach((t)-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //5.打印count的最终结果
        System.out.println("count的值为："+ atomicInteger.count);
    }
}
