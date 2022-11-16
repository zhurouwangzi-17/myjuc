package com.zhurouwangzi.juc.c_023_ConcurrencyContainer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentQueue里面增加的许多的同步方法
 * 以下代码运行，调用一次poll就从队列中取出一个元素，调用peek只是从队列中获取一个元素的值，并不会将此元素取出来
 */
public class T03_ConcurrentQueue {

    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        for(int i =0;i<10;i++){
            strs.offer("a"+i);
        }
        System.out.println(strs);
        System.out.println(strs.size());
        System.out.println("----------------");

        System.out.println(strs.poll());
        System.out.println(strs.size());
        System.out.println("----------------");

        System.out.println(strs.peek());
        System.out.println(strs.size());
    }
}
