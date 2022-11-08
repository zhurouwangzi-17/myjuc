package com.zhurouwangzi.juc.c_016_interview_01;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量的容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程和10个消费者线程
 *
 * 使用wait和notify/notifyAll来实现
 * 注意：
 * 1：生产者和消费者都是通过notifyAll来唤醒对方，使对方开始工作，这种方式不是太好
 * 因为notifyAll使唤醒所有正在睡眠中的线程，比如生产者调用notifyAll方法除了会唤醒消费者，还会唤醒其它的生产者线程
 * 2：get和put都要加synchronized关键词修饰，不加这个，很可能就使在生产者生产完将新对象放入集合中，但是count还没变的时候消费者线程读到来count值
 * 3：put和get方法判断集合中数目的时候一定要用while循环而不是用if判断，因为调用wait方法之后该线程会休眠，让出来锁，被唤醒之后它想要继续判断
 *   集合数量大小，就必须使用while来循环判断
 * 4：notify不会释放锁，wait会释放锁
 */
public class MyContainer1<T> {
    //定义集合对象
    final private LinkedList<T> lists = new LinkedList<>();
    //元素最大数量（最多10个元素）
    final private int MAX = 10;
    //定义元素数量
    private int count = 0;

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //消费者线程
        for(int i =0;i<10;i++){
            new Thread(()->{
                for(int j =0;j<5;j++){
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }

        //休眠2s
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //生产者线程
        for(int i =0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<25;j++){
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }
    }

    public synchronized void put(T t){
        while (lists.size()==MAX){//为什么要用while循环而不是用if判断
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程消费
    }

    public synchronized T get(){
        T t = null;
        while (lists.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者生产
        return t;
    }
}
