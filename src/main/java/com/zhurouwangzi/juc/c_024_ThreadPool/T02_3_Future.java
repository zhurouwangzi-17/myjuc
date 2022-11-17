package com.zhurouwangzi.juc.c_024_ThreadPool;

import java.util.concurrent.*;

/**
 * Future用来管理提交到线程池到任务
 * 上一个类测试类，我们调用ExecutorService的submit方法， 提交一个实现Callable接口的类过去
 * 返回值用Future接收，这种使用方法我们需要两个类来完成这个事情，一个是callable，一个是future
 * 现在我们可以用来另外一个类，来完成这件事情，只需要这一个类即可完成
 * 这个类只是演示类使用new Thread的方式来用FutureTask，并没有使用线程池的方式，这个后续再研究
 */
public class T02_3_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(()->{
           return "hello futureTask";
        });

        new Thread(futureTask).start();

        System.out.println(futureTask.get());//阻塞

    }
}
