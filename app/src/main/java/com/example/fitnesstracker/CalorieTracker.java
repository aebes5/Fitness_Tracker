package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.fitnesstracker.databinding.ActivityCalorieTrackerBinding;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CalorieTracker extends AppCompatActivity implements FoodAdapter.OnFoodClickListener {

    private ActivityCalorieTrackerBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<FoodItem> foodItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalorieTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve food items from SharedPreferences
        foodItemList = getFoodItemsFromSharedPreferences();

        // Initialize workouts ArrayList
        if (foodItemList == null) {
            foodItemList = new ArrayList<>();
        }

        foodAdapter = new FoodAdapter(foodItemList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.content.recyclerView.setLayoutManager(layoutManager);
        binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(foodAdapter);

        binding.buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        binding.buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFood(view);
            }
        });
    }

    @Override
    public void onFoodClick(int position) {

//        ViewFoodItem viewFoodItem = new ViewFoodItem();
//
//        viewFoodItem.setFoodList(foodItemList);
//        viewFoodItem.setPosition(position);
//
//
//        viewFoodItem.show(getSupportFragmentManager(), "ViewFoodItem");
    }

    public void addFood(View view) {
        AddFood addFood = new AddFood();
        addFood.show(getSupportFragmentManager(), "");
    }

    public void addFood(FoodItem foodItem) {
        foodItemList.add(foodItem);
        foodAdapter.notifyDataSetChanged();

        // Save the updated food items list to SharedPreferences
        saveFoodItemsToSharedPreferences(foodItemList);
    }
    public void deleteFood(FoodItem foodItem)
    {
        foodItemList.remove(foodItem);
        foodAdapter.notifyDataSetChanged();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveFoodItems(ArrayList<FoodItem> foodItems) {
        saveFoodItemsToSharedPreferences(foodItems);
    }

    private void saveFoodItemsToSharedPreferences(ArrayList<FoodItem> foodItems) {
        SharedPreferences sharedPreferences = getSharedPreferences("foodItems", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String foodItemsJson = gson.toJson(foodItems);

        editor.putString("foodItemsList", foodItemsJson);
        editor.apply();
    }

    private ArrayList<FoodItem> getFoodItemsFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("foodItems", MODE_PRIVATE);
        String foodItemsJson = sharedPreferences.getString("foodItemsList", "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<FoodItem>>(){}.getType();
        return gson.fromJson(foodItemsJson, type);
    }
}