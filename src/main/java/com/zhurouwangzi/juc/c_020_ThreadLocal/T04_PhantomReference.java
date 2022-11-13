package com.zhurouwangzi.juc.c_020_ThreadLocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用
 * 虚引用遇到gc一定会被回收
 * 一个对象是否有虚引用的存在，完全不会对其生存时间构成影响
 * 我们也无法通过虚引用来获取一个对象的实例
 * 为一个对象设置虚引用关联的唯一作用就是在这个对象被回收的时候收到一个系统通知
 *
 */
public class T04_PhantomReference {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        //new一个虚引用对象的时候，构造函数必须要传一个队列
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(),QUEUE);

        //t1线程
        new Thread(()->{
            while (true){
                LIST.add(new byte[1024*1024]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                System.out.println(phantomReference.get());
            }
        },"t1").start();

        //t2线程
        new Thread(()->{
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if(poll!=null){
                    System.out.println("虚引用对象被jvm回收了 "+poll);
                }
            }
        },"t2").start();

        //当前线程休眠1s
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
