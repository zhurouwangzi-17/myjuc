package com.zhurouwangzi.juc.c_014;

/**
 * 此包下的类在于总结使用多线程的几种方式，大类分为2种
 * 一种是继承Thread类，一种是实现Runnable接口，其它的都是这两种方式的变种
 * 此类是继承Thread的方式
 */
public class T_ExtendThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" run...");
    }

    public static void main(String[] args) {
        T_ExtendThread t_extendThread = new T_ExtendThread();

        //开启一个线程并start，会自动调用run方法
//        new Thread(t_extendThread,"t1").start();//指定线程名称

        //另外一种写法
        new Thread(t_extendThread).start();//不指定线程名称
    }
}
