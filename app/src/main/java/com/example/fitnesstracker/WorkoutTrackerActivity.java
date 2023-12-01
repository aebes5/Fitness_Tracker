package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.example.fitnesstracker.databinding.ActivityWorkoutTrackerBinding;

import java.util.ArrayList;


public class WorkoutTrackerActivity extends AppCompatActivity {

    private ActivityWorkoutTrackerBinding binding; //binding object
    private ArrayList<Workout> workouts;

    //view binding enabled

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        workouts = new ArrayList<Workout>();

        //reference button

        binding.buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        binding.buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWorkout(view);
            }
        });
    }

    //open activity
//i
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addWorkout(View view){
        AddWorkoutActivity addWorkout = new AddWorkoutActivity();
        addWorkout.show(getSupportFragmentManager(), "");
    }

    public void addWorkoutToList(Workout workout){
        workouts.add(workout);
    }

}

