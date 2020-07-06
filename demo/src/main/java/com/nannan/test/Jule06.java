package com.nannan.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: demo
 * @description:
 * @author: Juwenchao
 * @create: 2020-07-06 17:28
 **/
public class Jule06 {
    public static void main(String[] args) {


        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        
    }



}
