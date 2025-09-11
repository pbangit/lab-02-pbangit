package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText inputCity;
    private Button addButton;
    private Button confirmButton;
    private Button deleteButton;
    private ListView cityList;

    private ArrayList<String> dataList;
    private ArrayAdapter<String> adapter;
    private String selectedCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCity = findViewById(R.id.inputCity);
        addButton = findViewById(R.id.addButton);
        confirmButton = findViewById(R.id.confirmButton);
        deleteButton = findViewById(R.id.deleteCity);
        cityList = findViewById(R.id.city_list);


        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing","Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, dataList);
        cityList.setAdapter(adapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        inputCity.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.GONE);
        deleteButton.setVisibility(View.VISIBLE);


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            cityList.setItemChecked(position, true); // highlight selected row
            deleteButton.setVisibility(View.VISIBLE); // show delete button
        });


        addButton.setOnClickListener(v -> {
            inputCity.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
            inputCity.requestFocus();
        });


        confirmButton.setOnClickListener(v -> {
            String newCity = inputCity.getText().toString().trim();
            if (!newCity.isEmpty() && !dataList.contains(newCity)) {
                dataList.add(newCity);
                adapter.notifyDataSetChanged();
                inputCity.setText("");
                inputCity.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.GONE);
            }
        });


        deleteButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                adapter.notifyDataSetChanged();
                selectedCity = null;
                deleteButton.setVisibility(View.VISIBLE);
                cityList.clearChoices();
            }
        });
    }
}
