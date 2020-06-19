package com.nannan.test;

import java.util.concurrent.Semaphore;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-05 15:33:55
 *
 *
 *
 * 信号量是基于信号维度（通过传递同步信号量来协调线程的执行顺序）
 *
 *
 */
public class CustomCheckWindow {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <=5 ; i++) {
            new SecurityCheckThread(i,semaphore).start();
        }

    }



    private  static class SecurityCheckThread extends Thread{
        private int seq;
        private Semaphore semaphore;

        public SecurityCheckThread( int seq,Semaphore semaphore){
            this.seq = seq;
            this.semaphore = semaphore;
        }


        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("No."+seq+"乘客，正在查验中！");

                if (seq%2==0){
                    Thread.sleep(1000);
                    System.out.println("No."+seq+"乘客，身份可疑，不能出国！");
                }



            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println("No."+seq+"乘客已完成服务！");
            }
        }
    }
}
