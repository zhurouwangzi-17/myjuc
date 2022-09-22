package com.zhurouwangzi.juc.c_001;

/**
 * 此代码模拟的是给对象中的某个变量加锁
 * 任何线程想要执行m方法中的代码，就要拿到对象o的锁
 */
public class T {
    private int count = 10;
    private Object o = new Object();

    public void m(){
        synchronized (o){//任何线程想要执行以下代码都要拿到对象o的锁
            count--;
            System.out.println(Thread.currentThread().getName()+":   "+count);
        }
    }
}
