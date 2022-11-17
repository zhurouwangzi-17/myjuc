package com.zhurouwangzi.juc.c_025_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService相当于是对Executor对扩展
 * 它里边新增了许多管理线程对类
 */
public class T02_1_ExecutorService {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
