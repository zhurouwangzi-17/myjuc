package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * cyclic：循环
 * barrier：阻碍
 * CyclicBarrier：栅栏
 * 当达到一定的条件后，栅栏放开（执行指定的逻辑代码）
 * 然后里面竖起来，等待下一次达到条件之后再把栅栏放下
 */
public class T06_TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20,()->{
            System.out.println("人满，发车...");
        });

        for(int i =0;i<100;i++){
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
