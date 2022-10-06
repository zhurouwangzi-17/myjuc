package com.zhurouwangzi.juc.c_013;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的构造函数可以指定公平锁，它默认是不公平的
 * 设置为true的时候为公平锁，设置为false是非公平锁，默认是不公平的
 */
public class T04_ReentrantLock4 {
    Lock lock = new ReentrantLock(true);

}
