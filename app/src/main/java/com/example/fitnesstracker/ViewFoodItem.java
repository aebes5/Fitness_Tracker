package com.example.fitnesstracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.fitnesstracker.databinding.ActivityViewFoodItemBinding;

import java.util.ArrayList;

public class ViewFoodItem extends DialogFragment {

    private ArrayList<FoodItem> foodList;
    private int position;
    private ActivityViewFoodItemBinding binding;

    private CalorieTracker calorieTracker;

    public void setFoodList(ArrayList<FoodItem> foodList) {
        this.foodList = foodList;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        binding = ActivityViewFoodItemBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        FoodItem foodItem = foodList.get(position);

        binding.textViewName.setText(foodItem.getFoodName());
        binding.textViewCalories.setText(String.valueOf(foodItem.getCalories()));

        binding.buttonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position >= 0 && position < foodList.size()) {
                    foodList.remove(position);
                    CalorieTracker calorieTrackerActivity = (CalorieTracker) getActivity();
                    calorieTrackerActivity.deleteFood(foodItem);

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    int currentCalories = sharedPreferences.getInt("caloriesGained", 0);
                    int caloriesToDelete = foodItem.getCalories();
                    int updatedCalories = currentCalories - caloriesToDelete;

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("caloriesGained", updatedCalories);
                    editor.apply();

                    dismiss();
                } else {
                    dismiss();
                }
            }
        });

        return builder.create();
    }

}
