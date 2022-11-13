package com.zhurouwangzi.juc.c_020_ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * 此段代码执行结果，p的name值会打印出来lisi
 * 这是因为t1线程休眠2s，而t2线程休眠1s，t2线程休眠1s之后将p的name值修改掉
 * 等2s等之后，t1线程结束休眠打印p等值，打印的是修改之后的值
 * person对象用volatile修饰了，它保证线程之间对可见性
 */
public class ThreadLocal01 {
    static volatile Person p = new Person();

    public static void main(String[] args) {
        //t1线程,休眠2s之后打印p的name值
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(p.name);
        },"t1").start();

        //t2线程,休眠1s之后将p的name值改变
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            p.name="lisi";
        },"t2").start();
    }
}

class Person{
    String name = "zhangsan";
}
