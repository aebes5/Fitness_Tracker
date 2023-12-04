package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.fitnesstracker.databinding.ActivityViewFoodItemBinding;

import java.util.ArrayList;

public class ViewFoodItem extends DialogFragment {

    private ArrayList<FoodItem> foodList;
    private int position;
    private ActivityViewFoodItemBinding binding;


    public void setFoodList(ArrayList<FoodItem> foodList) {
        this.foodList = foodList;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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


  /*      binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditFood editDialog = new EditFoodDialogFragment();

                Bundle bundle = new Bundle();
                bundle.putString("FOOD_NAME", foodItem.getFoodName());
                bundle.putInt("FOOD_CALORIES", foodItem.getCalories());
                editDialog.setArguments(bundle);

                editDialog.show(getSupportFragmentManager(), "edit_food_dialog");
            }
        }); */

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position >= 0 && position < foodList.size()) {
                    foodList.remove(position);
                    CalorieTracker calorieTrackerActivity = (CalorieTracker) getActivity();
                    calorieTrackerActivity.deleteFood(foodItem);
                    dismiss();
                }
            }
        });



        return builder.create();
    }
}
