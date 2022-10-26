package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CountDownLatch的好处就是简单方便，并且还可以指定等待时间
 * 但是如果把t1中的线程休眠代码去掉，有可能在size为6或者7什么的时候t2才会打印，
 * 这是因为t1虽然执行到5把t2的门闩打开了，但是这个时候t1蹭蹭蹭往下执行，t2执行的比较慢
 * 解决这个问题的办法就是使用两个门闩，给t1加一个门闩，当size为5的时候用门闩闩住t1，把t2的门闩打开
 * t2执行完之后把t1的门闩打开
 */
public class T05_CountDownLatch {

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch testCountDownLatch = new T05_CountDownLatch();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        //t2线程
        new Thread(()->{
            if(testCountDownLatch.size()!=5){
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end...");
        },"t2").start();

        new Thread(()->{
            for(int i=0;i<10;i++){
                testCountDownLatch.add(new Object());
                System.out.println("list add "+i);
                if(testCountDownLatch.size()==5){
                    countDownLatch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
    }
}
