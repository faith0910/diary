package com.xunan.test;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-28 16:22:42
 */
public class ExtendsThread extends Thread{


    @Override
    public void run() {
        System.out.println("通过继承thread来创建线程");
    }

    public static void main(String[] args) {
        ExtendsThread extendsThread  = new ExtendsThread();
        extendsThread.run();
    }
}
