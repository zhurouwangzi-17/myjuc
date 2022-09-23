package com.zhurouwangzi.juc.c_006;

import java.util.concurrent.TimeUnit;


public class Account {
    private String accountName;//账户名
    private double balance;//账户余额

    public synchronized void setAccount(String accountName,double balance){
        this.accountName=accountName;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance(){
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(()->account.setAccount("zhangsan",10.10)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第一次读取账户余额："+account.getBalance());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第二次读取账户余额："+account.getBalance());
    }
}
