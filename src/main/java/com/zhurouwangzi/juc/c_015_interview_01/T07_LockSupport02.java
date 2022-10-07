package com.zhurouwangzi.juc.c_015_interview_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 此代码在t1中给t2unpark之后，自己也park以下，意思就是等t2执行完自己再执行
 * 而在t2线程里，结束之后也给t1unpark以下，这样t1就能继续往下执行
 */
public class T07_LockSupport02 {

    static Thread t1=null,t2=null;

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T07_LockSupport02 lockSupport = new T07_LockSupport02();

        //t2线程
        t2 = new Thread(()->{
            LockSupport.park();
            System.out.println("t2 end...");
            LockSupport.unpark(t1);
        },"t2");

        t1 = new Thread(()->{
            for(int i =0;i<10;i++){
                lockSupport.add(new Object());
                System.out.println("list add "+i);
                if(lockSupport.size()==5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        },"t1");

        t2.start();
        t1.start();
    }
}
