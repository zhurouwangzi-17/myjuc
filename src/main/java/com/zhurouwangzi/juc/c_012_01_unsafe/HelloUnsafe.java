package com.zhurouwangzi.juc.c_012_01_unsafe;

import sun.misc.Unsafe;

/**
 * 以下这段代码运行是直接报错的
 * Unsafe我们不能直接使用
 */
public class HelloUnsafe {
    static class M{
        private M(){}

        int i =0;
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}
