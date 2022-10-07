package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程安全的list集合，并且volatile修饰，也无法实现需求
 */
public class T02_WithSyncVolatile {

    //使用线程安全的list集合，并且用volatile修饰
    volatile List list = Collections.synchronizedList(new LinkedList<>());

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T02_WithSyncVolatile withSyncVolatile = new T02_WithSyncVolatile();

        new Thread(()->{
            for(int i =0;i<10;i++){
                withSyncVolatile.add(new Object());
                System.out.println("list add "+i);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        },"t1").start();


        new Thread(()->{
            while (true){
                if(withSyncVolatile.size()==5){
                    break;
                }
            }
            System.out.println("t2 end...");
        },"t2").start();
    }
}
