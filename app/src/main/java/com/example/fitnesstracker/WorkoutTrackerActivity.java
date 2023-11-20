package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.example.fitnesstracker.databinding.ActivityWorkoutTrackerBinding;


public class WorkoutTrackerActivity extends AppCompatActivity {

    private ActivityWorkoutTrackerBinding binding; //binding object

    //view binding enabled

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //reference button

        binding.buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    //open activity

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}