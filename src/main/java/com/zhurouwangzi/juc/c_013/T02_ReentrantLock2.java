package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以使用tryLock进行尝试锁定，锁定失败或者超过规定时间的话代码就继续往下执行
 */
public class T02_ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1(){
        try{
            //加锁
            lock.lock();
            System.out.println("m1 start...");
            for(int i =0;i<5;i++){
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
        boolean locked = false;
        try {
            locked = lock.tryLock(6, TimeUnit.SECONDS);
            System.out.println("m2...");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            if(locked){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T02_ReentrantLock2 reentrantLock = new T02_ReentrantLock2();
        new Thread(reentrantLock::m1,"t1").start();
        new Thread(reentrantLock::m2,"t2").start();
    }
}
