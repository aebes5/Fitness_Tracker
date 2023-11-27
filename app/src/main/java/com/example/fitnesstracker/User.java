package com.example.fitnesstracker;

public class User {
    private String name;
    private int age;
    private String sex;

    public User() {
        this.name = "";
        this.age = 0;
        this.sex = "";
    }

    public User(String name, int age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}


