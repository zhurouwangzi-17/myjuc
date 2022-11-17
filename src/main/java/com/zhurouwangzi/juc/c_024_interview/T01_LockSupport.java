package com.zhurouwangzi.juc.c_024_interview;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport来实现是最简单的
 */
public class T01_LockSupport {

    static Thread t1=null,t2=null;

    public static void main(String[] args) {
        //创建两个数组，这里用几个数字和字母来模拟即可
        char[] charNum = "123456".toCharArray();
        char[] charLetter = "ABCDEF".toCharArray();

        t1 = new Thread(()->{
            for(char letter:charLetter){
                System.out.print(letter);
                LockSupport.unpark(t2);//叫醒t2线程
                LockSupport.park();//阻塞当前线程
            }
        },"t1");

        t2 = new Thread(()->{
            for(char num:charNum){
                LockSupport.park();
                System.out.print(num);
                LockSupport.unpark(t1);
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
