package com.example.fitnesstracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import com.example.fitnesstracker.databinding.ActivityAddWorkoutBinding;

public class AddWorkout extends DialogFragment {

    private ActivityAddWorkoutBinding binding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        binding = ActivityAddWorkoutBinding.inflate(LayoutInflater.from(getContext()));

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

    public void save() {
        if (!binding.editTextText.getText().toString().isEmpty() && !binding.editTextText2.getText().toString().isEmpty() && (binding.radioButton3.isChecked() || binding.radioButton4.isChecked())) {
            String name = binding.editTextText.getText().toString();
            int duration = Integer.parseInt(binding.editTextText2.getText().toString());
            String type = "";

            if (binding.radioButton3.isChecked()) {
                type = "Cardio";
            } else if (binding.radioButton4.isChecked()) {
                type = "Strength Training";
            }

            Workout workout = new Workout(name, type, duration);
            WorkoutTracker workoutTracker = (WorkoutTracker) getActivity();
            workoutTracker.addWorkoutToList(workout);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            int currentWorkouts = sharedPreferences.getInt("workouts", 0);
            int updatedWorkouts = currentWorkouts + 1;

            int currentCaloriesBurned = sharedPreferences.getInt("caloriesBurned", 0);
            int updatedCaloriesBurned;

            if (type.equals("Cardio")) {
                updatedCaloriesBurned = currentCaloriesBurned + (duration * 10);
            } else {
                // Assuming type is "Strength Training"
                updatedCaloriesBurned = currentCaloriesBurned + (duration * 5);
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("workouts", updatedWorkouts);
            editor.putInt("caloriesBurned", updatedCaloriesBurned);
            editor.apply();

            dismiss();
        }
        else {
            Toast.makeText(getContext(), "Make sure the fields aren't empty.", Toast.LENGTH_LONG).show();
        }
    }

    public void clear() {
        binding.editTextText.setText("");
        binding.radioButton4.setChecked(true);
    }
}