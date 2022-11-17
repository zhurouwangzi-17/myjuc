package com.zhurouwangzi.juc.c_024_interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition本质上是一个等待队列
 */
public class T03_1_lock_condition {

    public static void main(String[] args) {
        //要打印的字符串
        char[] charNum = "123456".toCharArray();
        char[] charLetter = "ABCDEF".toCharArray();

        //定义锁和condition
        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        //t1线程
        new Thread(()->{
            try {
                lock.lock();
                for(char letter:charLetter){
                    System.out.print(letter);
                    conditionT2.signalAll();//叫醒打印阿拉伯数字的线程队列
                    conditionT1.await();//阻塞当前线程
                }
                conditionT2.signal();
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        //t2线程
        new Thread(()->{
            try {
                lock.lock();
                for(char num:charNum){
                    System.out.print(num);
                    conditionT1.signal();//叫醒打印字母的线程队列
                    conditionT2.await();//阻塞当前线程
                }
                conditionT1.signal();
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
