package com.zhurouwangzi.juc.c_024_ThreadPool;

import java.util.concurrent.*;

/**
 * Callable使用
 * 它和Runnable的用法差不多
 */
public class T02_2_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<String> future = executorService.submit(() -> {
//            return "hello callable";
//        });这个写法是用lambda表达式，是下边写法到简写
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello callable";
            }
        });//异步
        System.out.println(future.get());//阻塞
        executorService.shutdown();
    }
}
