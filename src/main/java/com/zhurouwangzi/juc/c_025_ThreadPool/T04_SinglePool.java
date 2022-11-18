package com.zhurouwangzi.juc.c_025_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * single：简单，见名知意这是创建一个简单的线程池
 * 以下代码是从msb粘贴过来
 */
public class T04_SinglePool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            final int j = i;
            service.execute(()->{
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }
    }
}
