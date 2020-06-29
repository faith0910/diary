package com.xunan.test;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-28 16:20:50
 */
public class RunableThread implements Runnable{

    @Override
    public void run() {
        System.out.println("通过实现Runnable创建的");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunableThread());
        thread.run();
    }
}
