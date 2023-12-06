package com.example.fitnesstracker;

import android.os.Parcelable;
import android.os.Parcel;

public class FoodItem implements Parcelable{
    private String foodName;
    private int calories;

    public FoodItem(String foodName, int calories){
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName(){
        return foodName;
    }

    public int getCalories(){
        return calories;
    }

    public void setFoodName(String aName){
        foodName = aName;
    }

    public void setCalories(int aCalories){
        calories = aCalories;
    }

    protected FoodItem(Parcel in) {
        foodName = in.readString();
        calories = in.readInt();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodName);
        dest.writeInt(calories);
    }
}
