package com.example.fitnesstracker;

public class FoodItem {
    private String foodName;
    private String calories;

    public FoodItem(String foodName, String calories){
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName(){
        return foodName;
    }

    public String getCalories(){
        return calories;
    }
}
