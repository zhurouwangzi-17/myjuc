package com.zhurouwangzi.juc.c_019_varhandle;

import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;

/**
 * 由于VarHandle是jdk9版本之后才有的，我们普遍还用的是jdk8，所以这里暂时注释掉，将本项目的jdk版本切换到jdk8
 */
public class TestVarHandle {

    int x = 8;

//    private static VarHandle handle;
//
//    static {
//        try {
//            handle = MethodHandles.lookup().findVarHandle(TestVarHandle.class, "x", int.class);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        TestVarHandle t = new TestVarHandle();
//
//        //plain read / write
//        System.out.println((int)handle.get(t));
//        handle.set(t,9);
//        System.out.println(t.x);
//
//        handle.compareAndSet(t, 9, 10);
//        System.out.println(t.x);
//
//        handle.getAndAdd(t, 10);
//        System.out.println(t.x);
//
//    }
}
