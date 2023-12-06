package com.example.fitnesstracker;

import android.os.Parcelable;
import android.os.Parcel;

public class Workout implements Parcelable{

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

    protected Workout(Parcel in) {
        name = in.readString();
        type = in.readString();
        duration = in.readInt();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(duration);
    }
}
