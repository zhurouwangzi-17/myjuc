package com.zhurouwangzi.juc.c_024_interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这段代码试图模拟使用多个线程来实现交替打印，但是是不行的
 * 因为for循环打印是在线程里边，开来几个线程就会打印几遍，算是没有实现
 * 想要多个线程交替打印的话，可以考虑condition+线程池来实现
 */
public class T03_2_lock_condition {
    public static void main(String[] args) {
        //要打印的字符串
        char[] charNum = "123456789".toCharArray();
        char[] charLetter = "ABCDEFGHI".toCharArray();

        //定义锁和condition
        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        //打印字母的线程
        for(int i=0;i<2;i++){
            new Thread(()->{
                try {
                    lock.lock();
                    for(char letter:charLetter){
                        System.out.println(Thread.currentThread().getName()+" "+letter);
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
        }

        //打印阿拉伯数字的线程
        for(int i=0;i<2;i++){
            new Thread(()->{
                try {
                    lock.lock();
                    for(char num:charNum){
                        System.out.println(Thread.currentThread().getName()+" "+num);
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
}
