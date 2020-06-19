package com.nannan.test;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-02 15:34:04
 */
public class AppTest {

    private static volatile AppTest appTest = null;
    private AppTest(){
        System.out.println("AppTest is init!");
    }

    public static AppTest getInstance(){
        if (appTest==null){
            synchronized (AppTest.class){
                if (appTest==null){
                    appTest = new AppTest();
                }
            }
        }
        return appTest;
    }

    public static void main(String[] args) {
//         int v = AppTest.getInstance().hashCode();
//        System.out.println(v);



        String s1  = new String("Java");
        String s2 = "Ja"+"va";
        String s3 = s1.intern();
        String s4 = "Java";
        System.out.println(s1==s3);  //false
        System.out.println(s2==s4);  //true
        System.out.println(s2==s3);  //true
        System.out.println(s2==s1);  //false
        System.out.println(s3==s4);  //true




    }
}
