package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Class-level variables
    private ListView cityList;
    private EditText inputCity;
    private Button addButton;
    private Button deleteButton;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList;
    private String selectedCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views AFTER setContentView
        cityList = findViewById(R.id.city_list); // match XML id
        inputCity = findViewById(R.id.inputCity);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteCity);

        // Initialize data
        String[] cities = {"Toronto", "Vancouver", "Calgary", "Montreal", "Edmonton"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // Initialize adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, dataList);
        cityList.setAdapter(adapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Add city
        addButton.setOnClickListener(v -> {
            String newCity = inputCity.getText().toString().trim();
            if (!newCity.isEmpty() && !dataList.contains(newCity)) {
                dataList.add(newCity);
                adapter.notifyDataSetChanged();
                inputCity.setText("");
            } else {
                Toast.makeText(this, "City already exists or is empty", Toast.LENGTH_SHORT).show();
            }

        });

        // Select city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            cityList.setItemChecked(position, true);
            deleteButton.setVisibility(View.VISIBLE);
        });

        // Delete city
        deleteButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                adapter.notifyDataSetChanged();
                selectedCity = null;
                cityList.clearChoices();
                deleteButton.setEnabled(false);
            } else {
                Toast.makeText(this, "No city selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
