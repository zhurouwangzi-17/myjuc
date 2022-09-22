package com.zhurouwangzi.juc.c_002;

/**
 * 这个代码模拟的是给这个类的实例对象加锁
 * 任何线程想要执行这个对象的m方法，就要先拿到这个对象的锁
 */
public class T {
    private int count = 10;

    public void m(){
        synchronized (this){//任何线程想要执行以下代码，就要先拿到此对象的锁
            count--;
            System.out.println(Thread.currentThread().getName()+":   "+count);
        }
    }
}
