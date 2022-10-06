package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock是需要手动释放锁的，所以我们在用Lock加锁的时候，要用try代码块包括起来，在finally中将锁释放掉
 * synchronized是自动释放锁的，当代码执行完或者遇到异常的时候，JVM会帮我们自动释放锁
 */
public class T01_ReentrantLock1 {
    //new出来一把锁对象
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            //这里的lock方法其实和synchronized(this)是一样的，是对当前对象进行加锁
            lock.lock();
            System.out.println("m1 start...");
            for(int i =0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            System.out.println("m1 end...");
            lock.unlock();
        }
    }

    void m2(){
        try{
            //加锁
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("m2......");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 reentrantLock1 = new T01_ReentrantLock1();
        new Thread(reentrantLock1::m1,"t1").start();
        new Thread(reentrantLock1::m2,"t2").start();
    }
}
