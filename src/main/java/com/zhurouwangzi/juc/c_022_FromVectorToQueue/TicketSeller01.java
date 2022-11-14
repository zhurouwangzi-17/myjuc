package com.zhurouwangzi.juc.c_022_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景：有N张火车票，每张票都有一个编号，现在有10个窗口对外售票，请写一个模拟程序
 * 此程序使用ArrayList来实现，由于ArrayList是线程不安全的，所以会出现问题
 */
public class TicketSeller01 {

    //定义容器
    static List<String> tickets = new ArrayList<>();

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
