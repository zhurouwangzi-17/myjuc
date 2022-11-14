package com.zhurouwangzi.juc.c_022_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 场景：有N张火车票，每张票都有一个编号，现在有10个窗口对外售票，请写一个模拟程序
 * 此代码使用ConcurrentQueue提高并发现，ConcurrentQueue是线程安全的，并且效率也高
 */
public class TicketSeller04 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for(int i=0;i<10000;i++){
            tickets.add("票 编号：" + i);
        }
    }

    public static void main(String[] args) {
        for(int i =0;i<100;i++){
            new Thread(()->{
                while (true){
                    String poll = tickets.poll();
                    if(poll==null){
                        break;
                    }else{
                        System.out.println("销售了--" + poll);
                    }
                }
            }).start();
        }
    }
}
