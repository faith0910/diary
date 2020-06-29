package com.xunan.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program:spring_study
 * @description:
 * @author:Juwenchao
 * @date:2020-06-29 14:56:44
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                if (name.equals("Thread-0")) {
                    name = "巨文超";
                } else {
                    name = "女朋友";
                }
                System.out.println(name + "到了体育场西");

                try {
                    cyclicBarrier.await();
                    System.out.println("跟" + name + "去夜上海吃东⻄~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }


}
