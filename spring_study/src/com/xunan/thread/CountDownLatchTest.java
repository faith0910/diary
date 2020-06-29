package com.xunan.thread;/**
 * @author wuchunjie
 * @date 2020/6/29
 */

import java.util.concurrent.CountDownLatch;

/**
 * @program:spring_study
 * @description:
 * @author:Juwenchao
 * @date:2020-06-29 14:47:37
 */
public class CountDownLatchTest {


    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("许楠终于写完了");
                countDownLatch.countDown();
            }
        }).start();

        for (int i = 0; i <5 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("其他员工需要等待许楠");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("许楠终于写完了，其它人可以开始了");
                }
            }).start();
        }

    }



}
