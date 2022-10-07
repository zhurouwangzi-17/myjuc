package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport中主要是park和unPark方法
 * 以下代码当t1线程中给t2unpark后，并不会里面打印t2 end
 * 这是因为t1线程中给t2unpark之后，t1线程会继续往下执行，而CPU执行的比较快所以达不到我们想要的效果
 */
public class T06_LockSupport {

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T06_LockSupport lockSupport = new T06_LockSupport();

        Thread t2 = new Thread(()->{
            System.out.println("t2 start");
            if(lockSupport.size()!=5){
                LockSupport.park();
            }
            System.out.println("t2 end...");
        },"t2");
        t2.start();

        Thread t1 = new Thread(()->{
            for(int i =0;i<10;i++){
                lockSupport.add(new Object());
                System.out.println("list add "+i);
                if(lockSupport.size()==5){
                    LockSupport.unpark(t2);
                }
            }
        },"t1");
        t1.start();
    }
}
