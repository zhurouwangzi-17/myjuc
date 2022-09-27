package com.zhurouwangzi.juc.c_011;

import java.util.concurrent.TimeUnit;

/**
 * 此代码模拟了volatile保证线程可见性的情况
 */
public class T01_HelloVolatile {
    /*volatile*/ boolean running = true;

    void m(){
        System.out.println("m start...");
        while (running){
            //在while循环中加了这句代码，即使running不加volatile修饰符
            //那么程序运行了一段时间后，也会读取到running的最新值从而结束循环
            //由此可见这句代码会使线程重新去堆中读取最新的变量值
            /*System.out.println("m 循环中...");*/
        }
        System.out.println("m end...");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t01 = new T01_HelloVolatile();
        //开启一个子线程来运行m方法
        new Thread(t01::m,"t1").start();

        //主线程休眠1秒用来模拟代码逻辑
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //将running的值改为false
        t01.running = false;
    }
}
