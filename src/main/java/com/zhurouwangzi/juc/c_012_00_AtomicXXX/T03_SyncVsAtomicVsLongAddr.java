package com.zhurouwangzi.juc.c_012_00_AtomicXXX;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 对int类型的数据进行原子性操作
 * 使用synchronized、AtomicLong、LongAddr三种方式的效率比较
 */
public class T03_SyncVsAtomicVsLongAddr {
    static volatile long count1 = 0;
    static AtomicLong count2 = new AtomicLong();
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        //0.线程的数组，设置1000个线程
        Thread[] threads = new Thread[1000];

        //1.synchronized操作
        //1.1定义一个对象用来加锁
        Object lockObj = new Object();
        for(int i =0;i< threads.length;i++){
            threads[i] = new Thread(()->{
                for(int j=0;j<10000;j++){
                    synchronized (lockObj){
                        count1++;
                    }
                }
            });
        }
        //1.2记录开始时间
        long count1Begin = System.currentTimeMillis();
        //1.3让所有线程开始工作
        for(Thread thread:threads){
            thread.start();
        }
        //1.4等待所有线程执行完
        for(Thread thread:threads){
            thread.join();
        }
        //1.5记录结束时间
        long count1End = System.currentTimeMillis();
        //1.6打印第一种方式耗费时间
        System.out.println("synchronized方式值为"+count1+"，耗时为："+(count1End-count1Begin));

        //2.Atomic操作
        for(int k = 0;k<threads.length;k++){
            threads[k] = new Thread(()->{
                for(int j = 0;j<10000;j++){
                    count2.incrementAndGet();
                }
            });
        }
        //2.1记录开始时间
        long count2Begin = System.currentTimeMillis();
        //2.2让所有线程开始工作
        for(Thread thread:threads){
            thread.start();
        }
        //2.3等待所有线程执行完
        for(Thread thread:threads){
            thread.join();
        }
        //2.4记录结束时间
        long count2End = System.currentTimeMillis();
        //2.5耗费时间
        System.out.println("Atomic方式值为"+count2.get()+"，耗时为："+(count2End-count2Begin));

        //3.LongAddr操作
        for(int h = 0;h<threads.length;h++){
            threads[h] = new Thread(()->{
                for(int j = 0;j<10000;j++){
                    count3.increment();
                }
            });
        }
        //3.1记录开始时间
        long count3Begin = System.currentTimeMillis();
        //3.2让所有线程开始工作
        for(Thread thread:threads){
            thread.start();
        }
        //3.3等待所有线程执行完
        for(Thread thread:threads){
            thread.join();
        }
        //3.4记录结束时间
        long count3End = System.currentTimeMillis();
        //3.5耗费时间
        System.out.println("LongAddr方式值为"+count3.longValue()+"，耗时为："+(count3End-count3Begin));
    }
}
