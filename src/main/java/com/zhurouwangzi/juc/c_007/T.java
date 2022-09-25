package com.zhurouwangzi.juc.c_007;

import java.util.concurrent.TimeUnit;

/**
 * 此段代码模拟synchronized重入场景
 * 直接使用的是主线程来模拟
 */
public class T {

    public synchronized void m1(){
        System.out.println("m1 start...");
        //当前线程休眠1s
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end...");
    }

    public synchronized void m2(){
        //当前线程休眠2s
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        //直接调用m1
        new T().m1();
    }
}
