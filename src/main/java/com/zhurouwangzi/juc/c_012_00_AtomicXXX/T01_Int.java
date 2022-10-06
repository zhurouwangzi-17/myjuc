package com.zhurouwangzi.juc.c_012_00_AtomicXXX;

import java.util.ArrayList;
import java.util.List;

/**
 * 此代码模拟多个线程对一个对象中的int类型字段进行自增操作
 * 这种情况这个count变量必须用volatile修饰，自增的方法也必须用synchronized修饰
 * 要不然数据就会有线程不安全问题
 */
public class T01_Int {
    volatile private int count = 0;

    synchronized void m(){
        for(int i =0;i<10000;i++){
            count++;
        }
    }

    public static void main(String[] args) {
        //1.new一个对象
        T01_Int t01_int = new T01_Int();
        //2.开10个线程分别调用m方法
        List<Thread> threadList = new ArrayList<>();
        for(int i =0;i<10;i++){
            threadList.add(new Thread(t01_int::m,"thread"+i));
        }
        //3.让10个线程都开始工作
        threadList.forEach((t)->t.start());
        //4.调用线程的join方法，让每个线程都执行完
        threadList.forEach((t)-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //5.将count的最终值打印出来
        System.out.println("count的值为："+ t01_int.count);
    }
}
