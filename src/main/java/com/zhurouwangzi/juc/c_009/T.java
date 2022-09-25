package com.zhurouwangzi.juc.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 此代码模拟线程执行过程中出现异常的情况
 * 如果没有捕获这个异常的话，这种情况下锁是会被释放的
 * 如果捕获了异常，这种情况下锁不会被释放
 */
public class T {
    private int count = 0;

    synchronized void m(){
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count="+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count==5){
                //程序在这里会抛出异常，如果代码没有去捕获异常的话，当前线程会把锁释放掉
                int i = 1/0;
                //如果捕获异常让程序正常运行下去，锁就不会被释放
//                try {
//                    int i = 1/0;
//                }catch (Exception ex){
//                    System.out.println("捕获了异常，锁不会被释放，程序继续执行...");
//                }
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        //1.t1线程
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r1,"t1").start();

        //2.t2线程
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r2,"t2").start();
    }
}
