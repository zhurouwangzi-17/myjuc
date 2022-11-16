package com.zhurouwangzi.juc.c_023_ConcurrencyContainer;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器：copy on write
 * 并发情况下，写读效率第，读读效率高
 * 适合写少读多读业务场景
 * CopyOnWriteArrayList底层其实是用ReentrantReadWriteLock实现的，读和写分别用两把锁
 * 在写读时候任何线程不能读，没有写操作读时候，其它的线程可以同时拿到锁去读数据
 */
public class T02_CopyOnWriteList {

    public static void main(String[] args) {
//        List<String> lists = new ArrayList<>();//并发情况下会有线程安全问题
//        List<String> lists = Collections.synchronizedList(new ArrayList<>());
//        List<String> lists = new Vector<>();
        List<String> lists = new CopyOnWriteArrayList<>();//写读时候效率慢，读的时候效率快
        Random random = new Random();
        Thread[] threads = new Thread[100];
        //写数据
        long startTime = System.currentTimeMillis();
        for(int i =0;i<threads.length;i++){
            threads[i] = new Thread(()->{
               for(int j =0;j<10000;j++){
                   lists.add("a"+random.nextInt(1000));
               }
            });
        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("写耗时："+(endTime-startTime));
        System.out.println(lists.size());

        //读数据
        startTime = System.currentTimeMillis();
        for(int i =0;i<lists.size();i++){
            lists.get(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("读耗时："+(endTime-startTime));
    }
}
