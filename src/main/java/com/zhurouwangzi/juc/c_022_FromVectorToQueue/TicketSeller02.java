package com.zhurouwangzi.juc.c_022_FromVectorToQueue;

import java.util.Vector;

/**
 * 场景：有N张火车票，每张票都有一个编号，现在有10个窗口对外售票，请写一个模拟程序
 * 此代码用Vector来模拟实现
 * 运行之后发现没问题，这是由于Vector的方法是线程安全的
 */
public class TicketSeller02 {
    //定义容器
    static Vector<String> tickets = new Vector<>();

    //初始化容器内的火车票
    static {
        for(int i =0;i<10000;i++){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        //开启10个线程同时售票
        for(int i =0;i<10;i++){
            new Thread(()->{
                while (tickets.size()>0){
                    System.out.println("销售了---"+tickets.remove(0));
                }
            }).start();
        }
    }
}
