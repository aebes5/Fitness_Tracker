package com.example.fitnesstracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.fitnesstracker.FoodItem;
import com.example.fitnesstracker.databinding.ActivityEditFoodBinding;
import com.example.fitnesstracker.databinding.ActivityViewFoodItemBinding;

import java.util.ArrayList;

public class EditFood extends DialogFragment {
    private FoodItem foodItem;
    private ActivityEditFoodBinding binding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = ActivityEditFoodBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(binding.getRoot());

        Bundle args = getArguments();
        if (args != null) {
            foodItem = args.getParcelable("foodItem");
            if (foodItem != null) {
                binding.editTextName.setText(foodItem.getFoodName());
                binding.editTextCalories.setText(String.valueOf(foodItem.getCalories()));
            }
        }

        binding.buttonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                dismiss();
            }
        });

        return builder.create();
    }

    public void save() {
        if (!binding.editTextName.getText().toString().isEmpty() && !binding.editTextCalories.getText().toString().isEmpty()) {
            String name = binding.editTextName.getText().toString();
            int caloriesGained = Integer.parseInt(binding.editTextCalories.getText().toString());

            FoodItem newFoodItem = new FoodItem(name, caloriesGained);
            CalorieTracker calorieTrackerActivity = (CalorieTracker) getActivity();
            calorieTrackerActivity.addFood(newFoodItem);
            if (foodItem != null) {
                calorieTrackerActivity.deleteFood(foodItem);

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                int currentCalories = sharedPreferences.getInt("caloriesGained", 0);
                int caloriesToDelete = foodItem.getCalories();
                int caloriesToAdd = newFoodItem.getCalories();
                int updatedCalories = currentCalories + caloriesToAdd - caloriesToDelete;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("caloriesGained", updatedCalories);
                editor.apply();
            } else {
                dismiss();
            }

            dismiss();
        } else {
            Toast.makeText(getContext(), "Make sure the fields aren't empty.", Toast.LENGTH_LONG).show();
        }
    }
}

