package com.zhurouwangzi.juc.c_025_ThreadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled: v  安排;为…安排时间;预定
 * 这个是专门给线程定时任务使用的线程池，但是定时任务一般用quartz或者其它专门的调度框架
 * 以下代码由msb粘贴过来
 */
public class T07_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}
