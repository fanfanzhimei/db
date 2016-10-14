package com.zhi.domain;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Person {
    private int personid;
    private String name;
    private int age;
    private int amount;

    public Person() {

    }

    public Person(String name, int age, int amount) {
        this.name = name;
        this.age = age;
        this.amount = amount;
    }

    public Person(int personid, String name, int age,int amount) {
        this.personid = personid;
        this.name = name;
        this.age = age;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person----:"
                + "personid:" + personid + ";name:" + name + ";age:" + age+";amount:"+amount;
    }
}