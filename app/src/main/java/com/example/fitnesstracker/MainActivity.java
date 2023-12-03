package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.fitnesstracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; //binding object

    //view binding enabled
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        TextView displayNameTextView = findViewById(R.id.textViewWelcome);
        String storedName = sharedPreferences.getString("name", "");

        if (!storedName.isEmpty()) {
            displayNameTextView.setText("Welcome, " + storedName + "!");
        } else {
            displayNameTextView.setText("Welcome!");
        }

        //reference buttons
        binding.buttonWorkoutTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutTrackerActivity();
            }
        });

        binding.buttonCalorieTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalorieTrackerActivity();
            }
        });

        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsActivity();
            }
        });
    }

    //open activities
    public void openWorkoutTrackerActivity() {
        Intent intent = new Intent(this, WorkoutTracker.class);
        startActivity(intent);
    }


    public void openCalorieTrackerActivity() {
        Intent intent = new Intent(this, CalorieTracker.class);
        startActivity(intent);
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
