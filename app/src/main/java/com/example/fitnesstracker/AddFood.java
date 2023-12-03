package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


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

        if (!binding.editTextName.getText().toString().isEmpty() && !binding.editTextCalories.getText().toString().isEmpty()) {
            String name = binding.editTextName.getText().toString();
            int calories = Integer.parseInt(binding.editTextCalories.getText().toString());

            FoodItem foodItem = new FoodItem(name, calories);
            CalorieTracker calorieTrackerActivity = (CalorieTracker) getActivity();
            calorieTrackerActivity.addFood(foodItem);
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