package com.nannan.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-05 15:14:33
 *
 *
 * countDownLatch是基于执行时间的同步类
 *
 */
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Thread thread1 = new TranslateThread("1st content",countDownLatch);
        Thread thread2 = new TranslateThread("2st content",countDownLatch);
        Thread thread3 = new TranslateThread("3st content",countDownLatch);

        thread1.start();
        thread2.start();
        thread3.start();
        countDownLatch.await(10, TimeUnit.SECONDS);
        System.out.println("所有线程执行完毕！");


    }


}
