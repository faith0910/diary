package com.nannan.test;

import java.util.*;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-02 16:16:42
 */
public class Data {
    public static void main(String[] args) {
        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");
        ListIterator<String> ait = a.listIterator();
        ait.next();
        ait.add("Gloria");
        System.out.println(a);


        Queue<String> queue  = new ArrayDeque<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");

        System.out.println(queue);
        queue.remove();

        System.out.println(queue);
        queue.offer("a");
        System.out.println(queue);


/*        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Faith");
        b.add("Halo");



//        for (String s :
//                a) {
//            System.out.println(s);
//        }
        ListIterator<String> ait = a.listIterator();
        Iterator<String> bit = b.iterator();
        while (bit.hasNext()){
            if (ait.hasNext())  ait.next();
            ait.add(bit.next());
        }*/

        Map<String,Integer> score = new HashMap<>();
        score.put("a",99);
        score.put("b",98);
        score.put("c",97);
        score.put("d",96);
        score.forEach((k,v)->System.out.println("key="+k+",value="+v));
        Set<String> strings = score.keySet();
        strings.stream().forEach(s -> System.out.println(s));
        Collection<Integer> collection = score.values();
        collection.stream().forEach(integer -> System.out.println(integer));
        Set<Map.Entry<String,Integer>> entrySet = score.entrySet();
        entrySet.stream().forEach(v-> System.out.println(v));
    }








}
