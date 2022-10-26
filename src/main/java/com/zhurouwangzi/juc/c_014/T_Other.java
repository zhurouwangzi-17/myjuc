package com.zhurouwangzi.juc.c_014;

/**
 * 这种方式既不继承Thread类，也不实现Runnable接口
 * 这种方式不用重写run方法，自己随意定义的方法名都可以
 */
public class T_Other {
    void m(){
        System.out.println(Thread.currentThread().getName()+" m run");
    }

    public static void main(String[] args) {
        T_Other t_other = new T_Other();
        //1、不指定线程名
//        new Thread(t_other::m).start();

        //2、指定线程名
//        new Thread(t_other::m,"t1").start();


        //3、
        new Thread(()->{
            t_other.m();
        },"t1").start();
    }
}
