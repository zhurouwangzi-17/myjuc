package com.zhurouwangzi.juc.c_023_ConcurrencyContainer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue的容量永远为0
 * 一般用来分生产者和消费者线程的队列
 * SynchronousQueue中的put和take方法都是阻塞方法
 * 并且put之后如果没有被消费就会一直阻塞直到这个元素被消费，take如果获取不到元素也会阻塞，直到能获取到元素为止
 * 所以SynchronousQueue的容量永远为0
 */
public class T06_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
        //strs.put("bbb");
//        strs.add("aaa");
        System.out.println(strs.size());
    }
}
