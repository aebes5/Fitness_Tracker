package com.example.fitnesstracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

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
        RadioGroup radioGroupSex = binding.radioGroupSex;
        RadioGroup radioGroupUnits = binding.radioGroupUnits;
        Switch switchDarkMode = binding.switchDarkMode;

        // Load settings from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Load name
        editTextName.setText(sharedPreferences.getString("name", ""));

        // Load age
        editTextAge.setText(String.valueOf(sharedPreferences.getInt("age", 0)));

        // Load sex
        String sex = sharedPreferences.getString("sex", "");
        // Set the selected radio button based on the loaded value
        if (sex.equals(getString(R.string.male))) {
            radioGroupSex.check(R.id.radioButtonMale);
        } else if (sex.equals(getString(R.string.female))) {
            radioGroupSex.check(R.id.radioButtonFemale);
        }

        // Load units
        String units = sharedPreferences.getString("units", "");
        // Set the selected radio button based on the loaded value
        if (units.equals(getString(R.string.imperial))) {
            radioGroupUnits.check(R.id.radioButtonImperial);
        } else if (units.equals(getString(R.string.metric))) {
            radioGroupUnits.check(R.id.radioButtonMetric);
        }

        // Load dark mode
        switchDarkMode.setChecked(sharedPreferences.getBoolean("darkMode", false));

        // Set click listener for the Save button
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save settings to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Save name
                editor.putString("name", editTextName.getText().toString());

                // Save age
                editor.putInt("age", Integer.parseInt(editTextAge.getText().toString()));

                // Save sex
                int selectedSexId = radioGroupSex.getCheckedRadioButtonId();
                RadioButton selectedSexRadioButton = findViewById(selectedSexId);
                editor.putString("sex", selectedSexRadioButton.getText().toString());

                // Save units
                int selectedUnitsId = radioGroupUnits.getCheckedRadioButtonId();
                RadioButton selectedUnitsRadioButton = findViewById(selectedUnitsId);
                editor.putString("units", selectedUnitsRadioButton.getText().toString());

                // Save dark mode
                editor.putBoolean("darkMode", switchDarkMode.isChecked());

                // Apply the changes
                editor.apply();
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