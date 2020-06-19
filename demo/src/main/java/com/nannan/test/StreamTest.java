package com.nannan.test;


import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-11 11:55:24
 */
public class StreamTest {

    /**
     * Stream的操作符大体分为2种
     * 1.中间操作符
     * 2.终止操作符
     */

    public static void main(String[] args) {

       /* Stream.of("apple", "banana", "orange", "lemon", "grape", "pear")
            .mapToDouble(e->e.length()).forEach(e -> System.out.println(e) );
//                .map(String::length).forEach(System.out::println);
*/


       /* Stream.of("a-b-c-d","e-f-i-g-h").flatMap(e->Stream.of(e.split("-")))
                .forEach(e-> System.out.println(e));*/

//        Stream.of(1,2,3,4,5,6).limit(3).forEach(e-> System.out.println(e));

//        Stream.of(1,3,2,4,3,2,4,5,3,2,4,5,1,1,3,3).distinct()
//                .forEach(integer -> System.out.println(integer));

       /* Stream.of(1,2,3,6,7,8,9,5,4).filter(e->e>=5).forEach(integer -> System.out.println(integer));
        // 跳过元素
        Stream.of(1,2,3,6,7,8,9,5,4).skip(5).forEach(integer -> System.out.println(integer));
*/


//        Stream.of(1,3,2,6,9,8,4,5,7).sorted().forEach(integer -> System.out.println(integer));


        Stream.of("iPhone","macBook","iPad","iWatch","iMusic")
                .collect(Collectors.toList())
                .forEach(System.out::println);

       long s =  Stream.of("iPhone","macBook","iPad","iWatch","iMusic")
                .count();
        System.out.println("s = " + s);

    }


}
