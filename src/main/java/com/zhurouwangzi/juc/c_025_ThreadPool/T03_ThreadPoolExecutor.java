package com.zhurouwangzi.juc.c_025_ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 在实际开发中我们要用这个ThreadPoolExecutor来创建线程池
 */
public class T03_ThreadPoolExecutor {

    static class Task implements Runnable{

        private int i;

        public Task(int i){
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Task"+i);
            try {
                System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        //1.创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        //2.循环向线程池中提交任务
        for(int i =0;i<8;i++){
            Future<?> submit = threadPoolExecutor.submit(new Task(i));
//            threadPoolExecutor.execute(new Task(i));
        }
        //3.打印线程池中的队列
        System.out.println(threadPoolExecutor.getQueue());

        //4.向线程池中继续提交任务，测试拒绝策略
        threadPoolExecutor.submit(new Task(9));
    }
}
