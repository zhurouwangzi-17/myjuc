package com.zhurouwangzi.juc.c_023_ConcurrencyContainer;

import java.util.concurrent.LinkedTransferQueue;

/**
 * TransferQueue接口的唯一实现类是LinkedTransferQueue
 */
public class T07_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");
        System.out.println(strs.size());
        strs.put("aaa");


//		new Thread(() -> {
//			try {
//				System.out.println(strs.take());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start();
    }
}
