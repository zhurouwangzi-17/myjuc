package com.zhurouwangzi.juc.c_004;

/**
 * 此代码模拟创建多个当前对象，分别给这些对象加锁
 * 这种写法会导致加锁没有任何意义
 */
public class T2 implements Runnable {
    private int count = 5;


    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+":   "+count);
    }

    public static void main(String[] args) {
        for (int i =0;i<5;i++){
            T2 t2 = new T2();
            new Thread(t2,"Thread"+i).start();
        }
    }
}
