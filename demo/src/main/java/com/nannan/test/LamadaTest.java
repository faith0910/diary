package com.nannan.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-11 18:22:18
 */
public class LamadaTest {


    public static void main(String[] args) {


      /*List<String> names2  = Stream.of("Google ","Apple ","HuaWei ","Vivo ","Sumsung ").collect(Collectors.toList());

        LamadaTest lamadaTest  = new LamadaTest();

        lamadaTest.sortUsingJava8(names2);

        System.out.println(names2);*/
        List<String> names =
                Arrays.asList("Geek","GeeksQuiz","g1","QA","Geek2");

        // declare the predicate type as string and use
        // lambda expression to create object
        Predicate<String> p = s->s.contentEquals("QA");

        // Iterate through the list
        for (String st:names)
        {
            // call the test method
            if (p.test(st))
                System.out.println(st);
        }



    }

    private void sortUsingJava8(List<String> names){
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }



}
