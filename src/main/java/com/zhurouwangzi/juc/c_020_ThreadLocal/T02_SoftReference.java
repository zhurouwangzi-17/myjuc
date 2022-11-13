package com.zhurouwangzi.juc.c_020_ThreadLocal;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 将此类运行是的jvm初始和最大内存大小都设置为20M，运行就会报错，第三次打印报错：java.lang.OutOfMemoryError: Java heap space
 * 如果不手动设置使用内存大小，那么它就不会报错，这是因为这个时候内存还够用。
 * 结论：软引用指向的对象，只有在内存不够用的时候gc才会回收软引用对象
 *      软引用是用来描述一些还有用但是非必须的对象
 *      对于软引用关联的对象，在系统将要发生内存溢出异常之前，才会把这些对象列入回收范围进行二次回收
 *      如果这次回收完之后还没有足够多的内存，那么才会抛出异常
 */
public class T02_SoftReference {
    public static void main(String[] args) {
        //1.创建一个byte数组类型的软引用对象，初始大小设置魏10M
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*1024*10]);
        //2.控制台打印弱引用对象
        System.out.println(softReference.get());
        //3.gc回收
        System.gc();
        //4.睡眠500毫秒
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //5.控制台打印弱引用对象
        System.out.println(softReference.get());

        //6.在内存中再创建一个数组，分配一块内存
        // 如果heap将要装不下，系统会垃圾回收，先回收一次，如果内存还是不够，会把软引用干掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(softReference.get());
    }
}
