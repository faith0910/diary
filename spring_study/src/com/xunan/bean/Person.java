package com.xunan.bean;

/**
 * @program:spring_study
 * @description:
 * @author:Juwenchao
 * @date:2020-06-29 14:16:33
 */

public class Person {
    private int id;
    private String name;
    private int age;
    private String gender;

    public Person() {
        System.out.println("创建bean");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
