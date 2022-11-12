package com.zhurouwangzi.juc.c_013;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 见名知意：读写锁
 * 读：共享锁
 * 写：排它锁
 */
public class T08_TestReadWriteLock {
    //ReentrantLock是排它的锁，无论读还是写，只有其它线程释放锁了之后其它线程才能够拿到锁
    static Lock lock = new ReentrantLock();
    private static int value;

    //读写锁
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();//读锁
    static Lock writeLock = readWriteLock.writeLock();//写锁

    //读操作
    public static void read(Lock readLock){
        try{
            readLock.lock();
            //模拟读的操作
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("read over....");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    //写操作
    public static void write(Lock writeLock,int v){
        try{
            writeLock.lock();
            //模拟写操作
            value = v;
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("write over...");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        //直接使用ReentrantLock排它锁
//        Runnable readRunnable = ()->read(lock);
//        Runnable writeRunnable =()->write(lock,new Random().nextInt());

        //读的时候使用读锁，写的时候使用写锁
        Runnable readRunnable = ()->read(readLock);
        Runnable writeRunnable = ()->write(writeLock,new Random().nextInt());

        //开启18个线程读
        for(int i =0;i<18;i++){
            new Thread(readRunnable).start();
        }

        //开启2个线程写
        for(int j =0;j<2;j++){
            new Thread(writeRunnable).start();
        }
    }
}
