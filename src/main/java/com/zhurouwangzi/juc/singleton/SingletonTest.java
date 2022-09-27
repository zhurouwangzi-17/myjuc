package com.zhurouwangzi.juc.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 饿汉式写法，并且加了双重检查
 * 注意：INSTANCE加不加volatile修饰的区别
 * 在实际开发过程中要加volatile
 */
public class SingletonTest {
    //1.定义单例对象
    private static /*volatile*/ SingletonTest INSTANCE = null;

    //2.将构造方法私有化，这样外界就无法通过new来获取该对象
    private SingletonTest(){}

    //3.对外界提供一个获取该对象的方法
    public static SingletonTest getInstance(){
        //3.1先检查对象是否为null，为null的话就进行初始化
        //不为null的话就直接将INSTANCE返回
        if(INSTANCE==null){
            //3.2将此静态class加锁
            synchronized (SingletonTest.class){
                //3.3再次判断INSTANCE是否为null，进行双重检查
                if(INSTANCE==null){
                    //3.4休眠1毫秒，模拟业务逻辑处理
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new SingletonTest();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->
                    System.out.println(SingletonTest.getInstance().hashCode())
            ).start();
        }
    }
}
