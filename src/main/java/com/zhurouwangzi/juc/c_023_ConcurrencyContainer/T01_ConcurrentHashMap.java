package com.zhurouwangzi.juc.c_023_ConcurrencyContainer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 跳表源码解析——http://blog.csdn.net/sunxianghuang/article/details/52221913
 * 以下代码模拟开启100个线程，分别向map容器里面添加10000个由随机数作为键值对对map对象
 * 通过不同类型的map容器运行代码测试，发现支持高并发的容器最终的size也不是100000，有点奇怪
 */
public class T01_ConcurrentHashMap {

    public static void main(String[] args) {
        //1.定义map对象
//        Map<String,String> map = new Hashtable<>();//线程安全，支持高并发但是效率偏慢，put方法是用synchronized修饰
//        Map<String,String> map = new HashMap<>();//线程不安全，不支持高并发，所以多个线程同时操作这个容器等时候就会出问题
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());//支持高并发，通过这个方法获取的容器是线程安全的
//        Map<String,String> map = new ConcurrentHashMap<>();//线程安全且效率高
        Map<String,String> map = new ConcurrentSkipListMap<>();//线程安全效率高，且排序，底层是用跳表实现
        //2.定义生成随机数对对象
        Random random = new Random();
        //3.定义线程数组
        Thread[] threads = new Thread[100];
        //4.定义门闩
        CountDownLatch latch = new CountDownLatch(threads.length);
        //5.循环创建线程
        long startTime = System.currentTimeMillis();
        for(int i =0;i<threads.length;i++){
            threads[i] = new Thread(()->{
               for(int j = 0;j<10000;j++){
                   map.put("a"+random.nextInt(100000),"a"+random.nextInt(100000));
               }
               latch.countDown();
            });
        }
        //6.循环开启线程
        for(Thread t:threads){
            t.start();
        }
        //7.等门闩计数器结束
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //8.记录结束时间并打印耗时
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时："+(endTime-startTime));
        //9.打印容器等size
        System.out.println(map.size());
    }
}
