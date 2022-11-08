package com.zhurouwangzi.juc.c_016_interview_01;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition可以更精确的唤醒线程，推荐使用这种方法
 */
public class MyContainer2<T> {
    //定义集合对象
    final private LinkedList<T> lists = new LinkedList<>();
    //元素最大数量（最多10个元素）
    final private int MAX = 10;
    //定义元素数量
    private int count = 0;

    Lock lock = new ReentrantLock();
    //生产者锁
    private Condition producer = lock.newCondition();
    //消费者锁
    private Condition consumer = lock.newCondition();

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

    public void put(T t){
        try {
            lock.lock();
            while (lists.size()==MAX){
                producer.await();//集合容器装满之后生产者线程休眠
            }
            lists.add(t);
            count++;
            consumer.signalAll();//通知消费者进行消费
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }


    public T get(){
        T t = null;
        try {
            lock.lock();
            while (lists.size()==0){
                consumer.await();//集合容器中没有数据消费者线程休眠
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();//通知生产者生产
        } catch (Exception ex){
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
        return t;
    }
}
