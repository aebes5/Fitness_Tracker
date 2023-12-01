package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fitnesstracker.databinding.ActivityCalorieTrackerBinding;

import java.util.ArrayList;

public class CalorieTrackerActivity extends AppCompatActivity implements FoodAdapter.OnContactClickListener {

    private ActivityCalorieTrackerBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<FoodItem> foodItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalorieTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        foodItemList = new ArrayList<>(); // Initialize foodItemList
        foodAdapter = new FoodAdapter(foodItemList, this); // Pass foodItemList and context

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.content.recyclerView.setLayoutManager(layoutManager);
        binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(foodAdapter);
    }

    public void addFood(View view) {
        // Implement logic to add food item
        // For example, open a dialog or activity to add a food item
        // Once added, update the foodItemList and notify the adapter
    }

    // Implement this method from FoodAdapter.OnContactClickListener interface
    @Override
    public void onContactClick(int position) {
        // Handle item click events here if needed
    }


}