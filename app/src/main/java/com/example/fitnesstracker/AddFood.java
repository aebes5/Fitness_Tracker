package com.example.fitnesstracker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddFood extends AppCompatDialogFragment {
    private EditText editTextFoodName;
    private EditText editTextCalories;
    private AddFoodListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_food, null);

        editTextFoodName = view.findViewById(R.id.editTextName);
        editTextCalories = view.findViewById(R.id.editTextCalories);


        return builder.create();
    }

    private void addFoodItem() {
        String foodName = editTextFoodName.getText().toString().trim();
        String calories = editTextCalories.getText().toString().trim();

        if (!foodName.isEmpty()) {
            listener.onFoodAdded(new FoodItem(foodName, calories));
            dismiss();
        } else {
            // Handle empty input or show an error message
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddFoodListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddFoodListener");
        }
    }

    public interface AddFoodListener {
        void onFoodAdded(FoodItem foodItem);
    }
}