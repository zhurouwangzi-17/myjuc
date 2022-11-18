package com.zhurouwangzi.juc.c_025_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * fixed：固定的，这个是产生一个线程数量大小固定的线程池
 * 以下代码将一个计算量比较大的操作，拆分为几段，根据规则拆分，拆分为几段创建段线程池里就设置几个线程
 * 这就是生产固定线程数量段线程池，newFixedThreadPool这个方法段入参是一个int类型段值，核心线程和最大线程数都用它
 * 分段用多线程来计算比一个线程计算段快的速度快
 * 以下代码由msb粘贴过来
 */
public class T06_FixedPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //1.由主线程单独计算
        long start = System.currentTimeMillis();
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //2.拆分为4段，创建线程数量为4段固定线程池，去并行段执行计算
        final int cpuCoreNum = 4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }

    }

    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for(int i=start; i<=end; i++) {
            if(isPrime(i)) results.add(i);
        }

        return results;
    }
}
