package com.zhurouwangzi.juc.c_024_ThreadPool;

import java.util.concurrent.Executor;

/**
 * 认识Executor
 */
public class T01_MyExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
//        command.run();//直接调用Runnable的run方法
        new Thread(command,"T").run();//直接调用Runnable的run方法
//        new Thread(command,"T").start();
    }

    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println(Thread.currentThread().getName()+"  hello executor"));
    }
}
