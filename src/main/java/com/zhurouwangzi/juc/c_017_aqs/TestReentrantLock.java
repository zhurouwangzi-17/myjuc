package com.zhurouwangzi.juc.c_017_aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS源码阅读
 */
public class TestReentrantLock {
    private static volatile int i =0;

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for(int i =0;i<500;i++){
            new Thread(()->{
                m();
            },"t1").start();
        }
    }

    public static void m(){
        try {
            lock.lock();
            i++;
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
