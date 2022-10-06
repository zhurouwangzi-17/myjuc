package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.CountDownLatch;

/**
 * CountDown：倒计时
 * Latch：门闩
 */
public class T05_CountDownLatch {
    public static void main(String[] args) {
        useJoin();
        useCountDownLatch();
    }

    private static void useCountDownLatch(){
        //1.定义线程数组
        Thread[] threads = new Thread[100];
        //2.定义一个倒数的计时器
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        //3.循环创建线程，并在每个线程执行完逻辑代码之后将计数器countDown一下
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0;j<1000;j++){
                    result +=j;
                }
                System.out.println(Thread.currentThread().getName()+":"+result);
                countDownLatch.countDown();
            });
        }
        //4.循环线程，让线程都开始工作
        for(int k = 0;k<threads.length;k++){
            threads[k].start();
        }

        //5.调用CountDownLatch的await方法
        //等待计数器归零（await在这里是阻塞的）
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void useJoin(){
        //1.定义线程数组
        Thread[] threads = new Thread[100];
        //2.循环创建线程
        for(int i =0;i<threads.length;i++){
            threads[i] = new Thread(()->{
               int result = 0;
               for(int j =0;j<1000;j++){
                   result+=j;
               }
                System.out.println(Thread.currentThread().getName()+":"+result);
            });
        }
        //3.循环开启线程
        for(int k = 0;k<threads.length;k++){
            threads[k].start();
        }
        //4.循环阻塞等待线程
        for(int h = 0;h<threads.length;h++){
            try {
                threads[h].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
