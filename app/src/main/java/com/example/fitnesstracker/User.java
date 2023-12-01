package com.example.fitnesstracker;

import android.content.SharedPreferences;

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
        if (age >= 1) {
            this.age = age;
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    // Convert User details to a String
    public String serialize() {
        return name + "," + age + "," + sex;
    }

    // Create a User object from a String
    public static User deserialize(String serialized) {
        String[] parts = serialized.split(",");
        if (parts.length == 3) {
            return new User(parts[0], Integer.parseInt(parts[1]), parts[2]);
        } else {
            // Handle error or return a default user
            return new User();
        }
    }

    // Save user details to SharedPreferences
    public void saveToPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_details", serialize());
        editor.apply();
    }

    // Load user details from SharedPreferences
    public static User loadFromPreferences(SharedPreferences preferences) {
        String serialized = preferences.getString("user_details", "");
        return deserialize(serialized);
    }
}



