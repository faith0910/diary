package com.nannan.test;

import java.util.concurrent.CountDownLatch;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-05 15:17:13
 */
public class TranslateThread extends Thread {

    private String content;
    private final CountDownLatch count;


    public TranslateThread(String content, CountDownLatch count) {
        this.content = content;
        this.count = count;
    }

    @Override
    public void run() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("原文件存在非法字符");
        }
        System.out.println(content + "的翻译已经完成，译文是");
        count.countDown();
    }
}
