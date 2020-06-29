package com.xunan.thread;

/**
 * @program:spring_study
 * @description:
 * @author:Juwenchao
 * @date:2020-06-29 15:20:13
 */
public class TT {

    private volatile static TT instance;

    private TT() {
        System.out.println(TT.class);
    }

    public static TT getInstance() {
        if (instance == null) {
            synchronized (TT.class) {
                if (instance == null) {
                    instance = new TT();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        TT.getInstance();
    }
}
