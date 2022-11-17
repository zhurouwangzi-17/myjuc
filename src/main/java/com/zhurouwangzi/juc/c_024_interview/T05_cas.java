package com.zhurouwangzi.juc.c_024_interview;

/**
 * 从msb粘贴过来的代码
 * 这种方式实现的关键点在于我们自己实现来cas操作，通过比对枚举值来判断阻塞和执行，从而实现交替
 * 这种方法了解即可，也没必要记住
 */
public class T05_cas {
    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1; //思考为什么必须volatile

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for (char c : aI) {
                while (r != ReadyToRun.T1) {}
                System.out.print(c);
                r = ReadyToRun.T2;
            }

        }, "t1").start();

        new Thread(() -> {

            for (char c : aC) {
                while (r != ReadyToRun.T2) {}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }
}
