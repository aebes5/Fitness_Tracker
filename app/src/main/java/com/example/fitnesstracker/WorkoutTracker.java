package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.example.fitnesstracker.databinding.ActivityWorkoutTrackerBinding;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WorkoutTracker extends AppCompatActivity {

    private ActivityWorkoutTrackerBinding binding; //binding object
    private ArrayList<Workout> workouts;
    private WorkoutAdapter workoutAdapter;

    //view binding enabled
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve workouts from SharedPreferences
        workouts = getWorkoutsFromSharedPreferences();

        // Initialize workouts ArrayList
        if (workouts == null) {
            workouts = new ArrayList<>();
        }

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

    private ArrayList<Workout> getWorkoutsFromSharedPreferences() {
        // Retrieve the workouts from SharedPreferences
        // You can use Gson library to convert the JSON string back to ArrayList

        SharedPreferences sharedPreferences = getSharedPreferences("workouts", MODE_PRIVATE);
        String workoutsJson = sharedPreferences.getString("workoutsList", "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Workout>>(){}.getType();
        return gson.fromJson(workoutsJson, type);
    }

    //open activity
//i
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addWorkout(View view){
        AddWorkout addWorkout = new AddWorkout();
        addWorkout.show(getSupportFragmentManager(), "");
    }

    public void addWorkoutToList(Workout workout) {
        if (workouts == null) {
            workouts = new ArrayList<>();
        }

        workouts.add(workout);
        workoutAdapter.notifyDataSetChanged();

        // Store the updated list in SharedPreferences
        saveWorkoutsToSharedPreferences(workouts);
    }

    private void saveWorkoutsToSharedPreferences(ArrayList<Workout> workouts) {
        // Use SharedPreferences to store the workouts
        SharedPreferences sharedPreferences = getSharedPreferences("workouts", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String workoutsJson = gson.toJson(workouts);

        editor.putString("workoutsList", workoutsJson);
        editor.apply();
    }

}

