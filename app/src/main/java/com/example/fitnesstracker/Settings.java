package com.example.fitnesstracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fitnesstracker.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {

    private ActivitySettingsBinding binding; // binding object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Reference UI elements
        EditText editTextName = binding.editTextName;
        EditText editTextAge = binding.editTextAge;
        EditText editTextWeight = binding.editTextWeight;
        RadioGroup radioGroupSex = binding.radioGroupSex;
        RadioGroup radioGroupUnits = binding.radioGroupUnits;

// Load settings from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        editTextName.setText(sharedPreferences.getString("name", ""));

        int age = sharedPreferences.getInt("age", -1);
        if (age != -1) {
            editTextAge.setText(String.valueOf(age));
        } else {
            editTextAge.setHint("Age");
        }

        int weight = sharedPreferences.getInt("weight", -1);
        if (weight != -1) {
            editTextWeight.setText(String.valueOf(weight));
        } else {
            editTextWeight.setHint("Weight");
        }

        String sex = sharedPreferences.getString("sex", "");
        if (sex.equals(getString(R.string.male))) {
            radioGroupSex.check(R.id.radioButtonMale);
        } else if (sex.equals(getString(R.string.female))) {
            radioGroupSex.check(R.id.radioButtonFemale);
        }

        String units = sharedPreferences.getString("units", "");
        if (units.equals(getString(R.string.imperial))) {
            radioGroupUnits.check(R.id.radioButtonImperial);
        } else if (units.equals(getString(R.string.metric))) {
            radioGroupUnits.check(R.id.radioButtonMetric);
        }

// Set click listener for the Save button
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save settings to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("name", editTextName.getText().toString());
                editor.putInt("age", Integer.parseInt(editTextAge.getText().toString()));
                editor.putInt("weight", Integer.parseInt(editTextWeight.getText().toString()));

                int selectedSexId = radioGroupSex.getCheckedRadioButtonId();
                RadioButton selectedSexRadioButton = findViewById(selectedSexId);
                editor.putString("sex", selectedSexRadioButton.getText().toString());

                int selectedUnitsId = radioGroupUnits.getCheckedRadioButtonId();
                RadioButton selectedUnitsRadioButton = findViewById(selectedUnitsId);
                editor.putString("units", selectedUnitsRadioButton.getText().toString());

                editor.apply();

                // Show Toast message
                Toast.makeText(getApplicationContext(), "User saved", Toast.LENGTH_SHORT).show();
            }
        });


        // Set click listener for the Clear button
        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear UI fields
                editTextName.setText("");
                editTextAge.setText("");
                editTextWeight.setText("");
                radioGroupSex.clearCheck(); // Uncheck all radio butt   ons in radioGroupSex
                radioGroupUnits.clearCheck(); // Uncheck all radio buttons in radioGroupUnits

                // Clear SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Toast.makeText(getApplicationContext(), "User info reset", Toast.LENGTH_SHORT).show();
            }
        });

        // Reference button
        binding.buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    // Open activity
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
