package com.zhurouwangzi.juc.c_020_ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * 此段代码使用ThreadLocal，模拟每个线程独有的资源
 * 也就是我们可以理解为ThreadLocal是线程的局部变量（阅读ThreadLocal源码会发现它是Thread类的一个变量，这也证实了ThreadLocal是局部变量的理解）
 * 以下代码运行起来之后，会打印null，这说明t1线程根本就没有读取到t2线程设置的person对象
 */
public class ThreadLocal02 {
    static ThreadLocal<Person2> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        //t1线程
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(tl.get());
        },"t1").start();

        //t2线程
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tl.set(new Person2());
        },"t2").start();

    }
}

class Person2{
    String name = "zhangsan";
}
