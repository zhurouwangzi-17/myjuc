package com.zhurouwangzi.juc.c_014;

/**
 * 实现Runnable接口的方式启动多线程
 */
public class T_ImplementsRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" run...");
    }

    public static void main(String[] args) {
        T_ImplementsRunnable t_implementsRunnable = new T_ImplementsRunnable();

        //不指定线程名字
//        new Thread(t_implementsRunnable).start();

        //指定线程名字
        new Thread(t_implementsRunnable,"t1").start();
    }
}
