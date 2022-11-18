package com.zhurouwangzi.juc.c_025_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * parallel：并行的意思
 * jdk8之后提供了流式编程，结合lambda来使用非常好用
 * 平时经常用的list.stream()是流式编程，但是它不式并行的，是由当前线程单独完成的
 * 而list.parallelStream()是并行流，它底层是用ForkJoinPool来实现的
 * 以下代码由msb粘贴过来
 */
public class T10_ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for(int i=0; i<10000; i++) nums.add(1000000 + r.nextInt(1000000));

        //System.out.println(nums);

        long start = System.currentTimeMillis();
        nums.forEach(v->isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //使用parallel stream api

        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T10_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
