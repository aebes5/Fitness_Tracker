package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.DividerItemDecoration;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.example.fitnesstracker.databinding.ActivityWorkoutTrackerBinding;

import java.util.ArrayList;


public class WorkoutTrackerActivity extends AppCompatActivity {

    private ActivityWorkoutTrackerBinding binding; //binding object
    private ArrayList<Workout> workouts;
    private WorkoutAdapter workoutAdapter;

    //view binding enabled

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        workouts = new ArrayList<Workout>();

        workoutAdapter = new WorkoutAdapter(this, workouts);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.content.recyclerView.setLayoutManager(layoutManager);
        binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(workoutAdapter);


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
        workoutAdapter.notifyDataSetChanged();
    }

}

