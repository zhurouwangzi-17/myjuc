package com.zhurouwangzi.juc.c_025_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * cache：缓存
 * 以下代码从msb粘贴过来
 */
public class T05_CachePool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        TimeUnit.SECONDS.sleep(80);

        System.out.println(service);
    }
}
