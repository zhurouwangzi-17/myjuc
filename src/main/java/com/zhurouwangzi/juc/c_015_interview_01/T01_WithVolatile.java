package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 分析下面这个程序，能完成这个功能吗？
 * 答：并不能，因为线程t1虽然往容器中添加了5个元素，并且容器的size也等于5了，但是由于t1和t2线程的不可见性
 *    使得t2线程无法获取到最新的size值。而且即使给list加volatile修饰也没用，因为对于引用类型的数据来说，
 *    volatile只能保证它的引用地址改变的情况下其它线程才可见，而这个list容器是往里添加了元素，但是引用地址没变
 *    所以即使使用volatile修饰也无法实现这个需求
 */
public class T01_WithVolatile {
    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T01_WithVolatile withVolatile = new T01_WithVolatile();
        new Thread(()->{
            for(int i =0;i<10;i++){
                withVolatile.add(new Object());
                System.out.println("t1 add "+i);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                if(withVolatile.size()==5){
                    break;
                }
            }
            System.out.println("t2 end...");
        },"t2").start();
    }
}
