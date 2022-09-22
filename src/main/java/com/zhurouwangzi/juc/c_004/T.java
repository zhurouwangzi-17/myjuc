package com.zhurouwangzi.juc.c_004;

/**
 * 此代码模拟给对象加锁效果
 * 给run方法加了synchronized关键字之后，才会得到依次递减的效果
 * 如果不加synchronized关键字，在main方法中每次循环都会创建一个线程，这些线程可以在同一时间操作count，所以数据就乱了
 */
public class T implements Runnable {
    private int count = 50;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+":   "+count);
    }

    public static void main(String[] args) {
        T t = new T();
        for(int i = 0;i<50;i++){
            new Thread(t,"THREAD"+i).start();
        }
    }
}
