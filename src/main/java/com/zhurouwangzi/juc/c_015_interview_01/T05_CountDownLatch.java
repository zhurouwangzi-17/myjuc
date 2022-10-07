package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CountDownLatch的好处就是简单方便，并且还可以指定等待时间
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
