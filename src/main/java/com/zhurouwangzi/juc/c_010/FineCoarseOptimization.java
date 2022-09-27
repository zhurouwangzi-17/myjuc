package com.zhurouwangzi.juc.c_010;

import java.util.concurrent.TimeUnit;

/**
 * 此代码模拟使用synchronized关键字的时候，锁的优化
 * 比较下边m1和m2方法，相对来说m2的效率会高一点，就是我们加锁的代码要尽量的少，这叫锁的细化
 * 但是还有一种情况，在一个方法中，需要加锁的地方比不需要加锁的地方多的多，那这样我们还不如直接给这个方法加锁，
 * 这样反而效率会高一点，这叫锁的粗化
 */
public class FineCoarseOptimization {
    private int count = 0;

     synchronized void m1(){
         //1.本段休眠模拟业务逻辑代码，这个业务逻辑不需要加锁
         try {
             TimeUnit.SECONDS.sleep(1);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         //2.这个自增操作表示需要加锁的业务逻辑代码
         count++;

         //3.本段休眠模拟业务逻辑代码，这个业务逻辑也不需要加锁
         try {
             TimeUnit.SECONDS.sleep(1);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }

     void m2(){
         //1.本段休眠模拟业务逻辑代码，这个业务逻辑不需要加锁
         try {
             TimeUnit.SECONDS.sleep(1);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         //2.这个自增操作表示需要加锁的业务逻辑代码
         synchronized (this){
             count++;
         }

         //3.本段休眠模拟业务逻辑代码，这个业务逻辑也不需要加锁
         try {
             TimeUnit.SECONDS.sleep(1);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
}
