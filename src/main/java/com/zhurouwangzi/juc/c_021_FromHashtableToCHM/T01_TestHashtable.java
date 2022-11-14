package com.zhurouwangzi.juc.c_021_FromHashtableToCHM;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Hashtable;
import java.util.UUID;

/**
 * 场景：使用100个线程，向容器中添加uuid作为键和值的对象，添加1000000个；然后使用100个线程，每个线程循环10000000次，获取容器的第10个元素
 * 此类使用Hashtable
 */
public class T01_TestHashtable {

    static Hashtable<UUID, UUID> m = new Hashtable<>();

    static int count = Constants.COUNT;
    static int thread_count = Constants.THREAD_COUNT;

    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];


    //以下初始化hashtable容器，并准备好向容器中添加的uuid键和值
    static {
        for(int i =0;i<count;i++){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    public static class MyThread extends Thread{
        int start;
        int gap = count/thread_count;

        public MyThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for(int i = start;i<start+gap;i++){
                m.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) {
        //1.向hashtable中添加uuid
        //1.1记录开始时间
        long startTime = System.currentTimeMillis();
        //1.2创建一个线程数组
        Thread[] threads = new Thread[thread_count];
        //1.3循环创建线程
        for(int i =0;i<thread_count;i++){
            threads[i] = new MyThread(i*(count/thread_count));
        }
        //1.4循环启动线程
        for(Thread t :threads){
            t.start();
        }
        //1.5循环阻塞线程，让每一个线程都执行完
        for(Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //1.6记录结束时间
        long endTime = System.currentTimeMillis();
        //1.7打印消耗时间
        System.out.println("添加元素所消耗时间："+(endTime-startTime));
        //1.8打印容器内元素个数，如果是1000000说明添加的元素没问题
        System.out.println("容器内元素个数："+m.size());

        //2.从hashtable中获取uuid
        //2.1记录开始时间
        startTime = System.currentTimeMillis();
        //2.2循环线程数组
        for(int i =0;i<thread_count;i++){
            threads[i] = new Thread(()->{
                for(int j =0;j<10000000;j++){
                    //循环10000000次，每次取容器里的第10个元素
                    m.get(keys[10]);
                }
            });
        }
        //2.3循环启动线程
        for(Thread t:threads){
            t.start();
        }
        //2.4循环阻塞线程
        for(Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("获取元素消耗时间："+(endTime-startTime));
    }
}
