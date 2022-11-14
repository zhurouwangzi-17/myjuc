package com.zhurouwangzi.juc.c_022_FromVectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 场景：有N张火车票，每张票都有一个编号，现在有10个窗口对外售票，请写一个模拟程序
 * 此代码使用LinkedList容器来实现，但是我们发现单纯的使用LinkedList也同样是有问题的，LinkedList是线程不安全的
 * 此段代码我们将票的数量调整为10000，将窗口（线程）的数量调整为100，运行发现还是会报错的
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 下面这个程序，只有保证size和remove是同一个原子操作，才能满足我们对需求
 */
public class TicketSeller03 {
    static List<String> tickets = new LinkedList<>();


    static {
        for(int i=0; i<10000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {

        for(int i=0; i<100; i++) {
            new Thread(()->{
//                while (tickets.size()>0){
//                    System.out.println("销售了--" + tickets.remove(0));
//                }
                while(true) {
                    synchronized(tickets) {
                        if(tickets.size() <= 0) break;
                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
