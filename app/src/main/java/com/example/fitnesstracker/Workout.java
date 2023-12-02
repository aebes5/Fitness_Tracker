package com.example.fitnesstracker;

public class Workout {

    private String name;
    private String type;

    private int duration;

    public Workout(String name, String type, int duration){
        this.name = name;
        this.type = type;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
