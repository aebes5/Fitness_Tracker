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


import com.example.fitnesstracker.databinding.ActivityEditWorkoutBinding;


import java.util.ArrayList;

public class EditWorkout extends DialogFragment {
    private Workout workout;
    private ActivityEditWorkoutBinding binding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = ActivityEditWorkoutBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(binding.getRoot());

        Bundle args = getArguments();
        if (args != null) {
            workout = args.getParcelable("workout");
            if (workout != null) {
                binding.editTextText.setText(workout.getName());
                if (workout.getType() == "Cardio") {
                    binding.radioButton3.setChecked(true);
                } else {
                    binding.radioButton4.setChecked(true);
                }

                binding.editTextText2.setText(String.valueOf(workout.getDuration()));
            }
        }

        binding.dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
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


            Workout newWorkout = new Workout(name, type, duration);
            WorkoutTracker workoutTracker = (WorkoutTracker) getActivity();
            workoutTracker.addWorkoutToList(newWorkout);

            if (workout != null) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                int currentCaloriesBurned = sharedPreferences.getInt("caloriesBurned", 0);

                int previousWorkoutBurnedCalories;

                if (workout.getType().equals("Cardio")) {
                    previousWorkoutBurnedCalories = workout.getDuration() * 10;
                } else {
                    // Assuming type is "Strength Training"
                    previousWorkoutBurnedCalories = workout.getDuration() * 5;
                }

                int newWorkoutBurnedCalories = 0;

                if (type.equals("Cardio")) {
                    newWorkoutBurnedCalories = duration * 10;
                } else {
                    // Assuming type is "Strength Training"
                    newWorkoutBurnedCalories = duration * 5;
                }

                int updatedCaloriesBurned = currentCaloriesBurned - previousWorkoutBurnedCalories + newWorkoutBurnedCalories;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("caloriesBurned", updatedCaloriesBurned);
                editor.apply();

                workoutTracker.deleteWorkout(workout);
            } else {
                Toast.makeText(getContext(), "Make sure the fields aren't empty.", Toast.LENGTH_LONG).show();
            }

            workoutTracker.deleteWorkout(workout);

        }
    }
}

