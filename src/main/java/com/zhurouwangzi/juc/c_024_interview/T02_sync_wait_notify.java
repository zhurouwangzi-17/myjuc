package com.zhurouwangzi.juc.c_024_interview;

/**
 * 这个是最基本的实现方法，很实用，但是和LockSupport比起来稍微有的麻烦
 */
public class T02_sync_wait_notify {

    static Thread t1=null,t2=null;

    public static void main(String[] args) {
        final Object o = new Object();
        //创建两个数组，这里用几个数字和字母来模拟即可
        char[] charNum = "123456".toCharArray();
        char[] charLetter = "ABCDEF".toCharArray();

        t1 = new Thread(()->{
            synchronized (o){
                for(char letter:charLetter){
                    try {
                        System.out.print(letter);
                        o.notify();//叫醒t2
                        o.wait();//阻塞自己
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();//这个是必须的，否则程序会一直阻塞
            }
        },"t1");

        t2 = new Thread(()->{
            synchronized (o){
                for(char num:charNum){
                    try {
                        System.out.print(num);
                        o.notify();//叫醒其它正在等待拿这把锁的线程，即t1线程
                        o.wait();//释放锁
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
