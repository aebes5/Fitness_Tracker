package com.example.fitnesstracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.fitnesstracker.databinding.ActivityViewWorkoutBinding;

import java.util.ArrayList;

public class ViewWorkout extends DialogFragment {

    private ArrayList<Workout> workoutList;
    private int position;
    private ActivityViewWorkoutBinding binding;
    private WorkoutAdapter workoutAdapter;

    private WorkoutTracker workoutTracker;

    public void setWorkoutList(ArrayList<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setWorkoutAdapter(WorkoutAdapter adapter) {
        this.workoutAdapter = adapter;
    }

    public void setWorkoutTracker(WorkoutTracker tracker) {
        this.workoutTracker = tracker;
    }

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        binding = ActivityViewWorkoutBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        Workout workout = workoutList.get(position);

        binding.textViewWorkoutName.setText(workout.getName());
        binding.textViewWorkoutType.setText(workout.getType());
        binding.textViewDuration.setText(String.valueOf(workout.getDuration()));

        binding.buttonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position >= 0 && position < workoutList.size()) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    int currentWorkouts = sharedPreferences.getInt("workouts", 0);
                    int updatedWorkouts = currentWorkouts - 1;

                    int currentCaloriesBurned = sharedPreferences.getInt("caloriesBurned", 0);
                    int updatedCaloriesBurned;

                    if (workout.getType().equals("Cardio")) {
                        updatedCaloriesBurned = currentCaloriesBurned - (workout.getDuration() * 10);
                    } else {
                        // Assuming type is "Strength Training"
                        updatedCaloriesBurned = currentCaloriesBurned - (workout.getDuration() * 5);
                    }

                    workoutList.remove(position);
                    WorkoutTracker workoutTracker1 = (WorkoutTracker) getActivity();
                    workoutTracker1.deleteWorkout(workout);


                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("caloriesBurned", updatedCaloriesBurned);
                    editor.putInt("workouts", updatedWorkouts);
                    editor.apply();

                    dismiss();
                } else {
                    dismiss();
                }
            }
        });
        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 EditWorkout editWorkout = new EditWorkout();


                Bundle bundle = new Bundle();
                bundle.putParcelable("workout", (Parcelable) workout);
                editWorkout.setArguments(bundle);


                editWorkout.show(getParentFragmentManager(), "workout");

                dismiss();
            }
        });

        return builder.create();
    }
}

