package com.example.fitnesstracker;

import android.content.Context;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.example.fitnesstracker.databinding.ActivityAddFoodBinding;

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

        if (!binding.editTextName.getText().toString().isEmpty() && !binding.editTextCalories.getText().toString().isEmpty()) {
            String name = binding.editTextName.getText().toString();
            int caloriesGained = Integer.parseInt(binding.editTextCalories.getText().toString());

            FoodItem foodItem = new FoodItem(name, caloriesGained);
            CalorieTracker calorieTrackerActivity = (CalorieTracker) getActivity();
            calorieTrackerActivity.addFood(foodItem);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            int currentCalories = sharedPreferences.getInt("caloriesGained", 0);
            int caloriesToAdd = foodItem.getCalories();
            int updatedCalories = currentCalories + caloriesToAdd;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("caloriesGained", updatedCalories);
            editor.apply();

            dismiss();
        }
        else {
            Toast.makeText(getContext(), "Make sure the fields aren't empty." , Toast.LENGTH_LONG).show();
        }
    }
    public void clear(){
        binding.editTextName.setText("");
        binding.editTextCalories.setText("");
    }
}