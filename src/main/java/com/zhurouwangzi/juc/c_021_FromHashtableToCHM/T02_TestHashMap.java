package com.zhurouwangzi.juc.c_021_FromHashtableToCHM;

import java.util.HashMap;
import java.util.UUID;

/**
 * 场景：使用100个线程，向容器中添加uuid作为键和值的对象，添加1000000个；然后使用100个线程，每个线程循环10000000次，获取容器的第10个元素
 * 此类使用HashMap来模拟，只不过hashmap是线程不安全的，所以其实这个模拟无意义，但是为了更好的理解容器的演化，此模拟代码也展示出来
 */
public class T02_TestHashMap {

    static HashMap<UUID,UUID> m = new HashMap<>();

    static int count = Constants.COUNT;
    static final int thread_count = Constants.THREAD_COUNT;

    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];

    static {
        for(int i =0;i<count;i++){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread{
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
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[thread_count];
        for(int i =0;i<thread_count;i++){
            threads[i] = new MyThread(i*(count/thread_count));
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
        System.out.println("添加元素所消耗时间："+(endTime-startTime));
        System.out.println("容器内元素个数："+m.size());
    }
}
