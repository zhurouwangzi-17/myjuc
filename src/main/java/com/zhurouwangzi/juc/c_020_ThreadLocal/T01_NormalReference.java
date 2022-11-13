package com.zhurouwangzi.juc.c_020_ThreadLocal;

import java.io.IOException;

/**
 * 强引用
 * Normal表示正常的意思，这个类来模拟强引用
 * 运行以下代码运行结果是控制台会打印finalize字符串，说明在gc回收的时候调用来finalize方法
 * 强引用：只有在引用被干掉之后才会被回收掉
 */
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();

        System.in.read();
    }
}
