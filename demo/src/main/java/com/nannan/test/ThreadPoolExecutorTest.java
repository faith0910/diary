package com.nannan.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-12 17:02:09
 */
public class ThreadPoolExecutorTest {


    public static void main(String[] args)  {

        /**
         * 创建一个可缓存线程池
         */
  /*      ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {
            final int index = i;
            try {
                Thread.sleep(index*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }*/

        /**
         * 创建一个指定工作线程数量的线程池
         */

      /*  ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i <10 ; i++) {
             final int tem = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(tem);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
*/

        /**
         * 创建一个单线程化的Executor
         */
      /*  ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i <10 ; i++) {
            final int start = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(start);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }*/

        /**
         * 创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。
         */
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },3, TimeUnit.SECONDS);
    }
}
