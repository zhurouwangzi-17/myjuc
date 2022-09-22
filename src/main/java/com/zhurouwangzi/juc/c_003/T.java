package com.zhurouwangzi.juc.c_003;

/**
 * 此代码模拟直接给方法加锁
 */
public class T {
    private int count = 10;

    public synchronized void m(){//等同于synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName()+":   "+count);
    }
}
