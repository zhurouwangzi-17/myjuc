package com.zhurouwangzi.juc.c_020_ThreadLocal;

/**
 * 新建一个类，重写父类Object的finalize方法
 * 当该对象被GC回收当时候会调用这个方法
 */
public class M {

    @Override
    protected void finalize() throws Throwable{
        System.out.println("finalize");
    }
}
