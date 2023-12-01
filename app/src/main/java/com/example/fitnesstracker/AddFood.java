package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.fitnesstracker.databinding.ActivityAddFoodBinding;
import com.example.fitnesstracker.databinding.ActivityAddWorkoutBinding;

public class AddFood extends DialogFragment {

    private ActivityAddFoodBinding binding;

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {

        binding = ActivityAddFoodBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                dismiss();

            }
        });


        return builder.create();
    }

    public void save(){
        String name = binding.editTextName.getText().toString();
        String calories = binding.editTextCalories.getText().toString();

        FoodItem foodItem = new FoodItem(name, calories);
        CalorieTrackerActivity calorieTrackerActivity = (CalorieTrackerActivity) getActivity();
        calorieTrackerActivity.addFood(foodItem);
        dismiss();
    }
    public void clear(){
        binding.editTextName.setText("");
        binding.editTextCalories.setText("");
    }
}