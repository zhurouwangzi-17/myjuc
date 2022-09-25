package com.zhurouwangzi.juc.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 此代码模拟继承类的可重入锁
 */
public class T {

    //父类中方法
    synchronized void m(){
        System.out.println("m start ...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end...");
    }

    public static void main(String[] args) {
        new TT().m2();
    }
}

/**
 * 子类
 */
class TT extends T{

    synchronized void m2(){
        System.out.println("child start");
        super.m();
        System.out.println("child end");
    }
}
