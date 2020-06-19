package com.nannan.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-15 17:51:35
 */
public class JuneFifteen {


    public static void main(String[] args) {

        maxCount2();
    }


    public static void sort() {
        int a[] = {1, 2, 3, 4, 5};
        int tem = 0;
        for (int i = 0; i < a.length / 2; i++) {
            tem = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = tem;

        }
        System.out.println(Arrays.toString(a));
    }

    public static void sort2() {
        int a[] = {1, 2, 3, 4, 5};
        int b[] = new int[5];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        for (int i = 0; i < a.length; i++) {
            b[a.length - i - 1] = a[i];
        }
        System.out.println(Arrays.toString(b));
    }

    public static void maxCount() {
        int a[] = {1, 2, 3, 4, 5, 6, 7, 7, 7, 8, 9};
        int val_max = -1;
        int time_max = 0;
        int time_tem = 0;
        for (int i = 0; i < a.length; i++) {
            time_tem = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[i] == a[j]) {
                    time_tem += 1;
                }
                if (time_tem > time_max) {
                    time_max = time_tem;
                    val_max = a[i];
                }
            }
        }
        System.out.println(val_max);
    }
    public static void maxCount2() {
        int a[] = {1, 2, 3, 4, 5, 6, 7, 7, 7, 8, 9};
        Map<Integer,Integer> d = new HashMap<>();
        for (int i = 0; i <a.length ; i++) {
            if (d.containsKey(a[i])){
                d.put(a[i],d.get(a[i])+1);
            }else {
                d.put(a[i],1);
            }
        }
        int val_max = -1;
        int time_max = 0;
        int count = 0;
        for (Integer k:d.keySet()
             ) {
            if (d.get(k)>time_max){
                time_max =d.get(k);
                val_max = count;
            }
            count++;
        }
        System.out.println(d);
    }

    StringBuffer s   = new StringBuffer();

    StringBuilder ss   = new StringBuilder();
}
