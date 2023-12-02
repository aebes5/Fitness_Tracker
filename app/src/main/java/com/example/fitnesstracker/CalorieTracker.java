package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitnesstracker.databinding.ActivityCalorieTrackerBinding;

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

        foodItemList = new ArrayList<>();
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
        // Handle food item click here
        // You can add logic to handle the click event for a food item
    }

    public void addFood(View view) {
        AddFood addFood = new AddFood();
        addFood.show(getSupportFragmentManager(), "");
    }


    public void addFood(FoodItem foodItem) {
        foodItemList.add(foodItem);
        foodAdapter.notifyDataSetChanged();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
