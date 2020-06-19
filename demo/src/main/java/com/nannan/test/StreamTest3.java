package com.nannan.test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-11 17:00:13
 */
public class StreamTest3 {
    public static void main(String[] args) {
        Optional<String> optional = Stream.of("iPhone", "macBook", "iPad", "iWatch", "iMusic").findFirst();
        optional.ifPresent(e -> System.out.println(e));

        Optional<String> stringOptional = Stream.of("iPhone", "macBook", "iPad", "iWatch", "iMusic")
                .parallel().findAny();
        stringOptional.ifPresent(e -> System.out.println(e));

        boolean res = Stream.of("iPhone", "macBook", "iPad", "iWatch", "iMusic")
                .noneMatch(e->e.equals("macBook"));
        System.out.println(res);
        Optional<Integer> integer =Stream.of(18,37,27,32,98,34,20).max((e1,e2)->e1.compareTo(e2));

        integer.ifPresent(System.out::println);

        int sum =Stream.of(18,37,27,32,98,34,20).reduce(0,(e1,e2)->e1+e2);
        System.out.println(sum);
        // forEachOrdered保证迭代的有序性
        Stream.of(0,2,6,5,4,9,8,-1).parallel().forEachOrdered(e->{
            System.out.println(Thread.currentThread().getName()+":"+e);
        });
        Object objectOptional[] =Stream.of(0,2,6,5,4,9,8,-1).toArray();
        int s  = objectOptional.length;
        System.out.println(s);
    }


}
