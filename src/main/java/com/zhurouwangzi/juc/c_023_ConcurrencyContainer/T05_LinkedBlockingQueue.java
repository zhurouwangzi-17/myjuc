package com.zhurouwangzi.juc.c_023_ConcurrencyContainer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue接口的实现类：LinkedBlockingQueue
 */
public class T05_LinkedBlockingQueue {
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>(10);
//    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++){
            strs.put("a"+i);
        }

        //add方法的测试代码
        boolean bSuss = strs.add("b");
        System.out.println(bSuss);
        System.out.println(strs);

        //offer方法的测试代码
//        boolean dSuss = strs.offer("d");
//        System.out.println(dSuss);
//        System.out.println(strs);


        //put方法的测试代码
//        strs.put("c");
//        System.out.println(strs);

        //poll的测试代码
//        System.out.println(strs.poll());
//        System.out.println(strs);

        //peek的测试代码
//        System.out.println(strs.peek());
//        System.out.println(strs);
    }
}
