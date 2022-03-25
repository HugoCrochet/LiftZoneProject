package com.example.liftzone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddWorkout extends AppCompatActivity {

    //DBHandler db;
    Button searchMuscles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_workout);

        //db = new DBHandler(this);
        searchMuscles = findViewById(R.id.search_button);

        searchMuscles.setOnClickListener(view -> {
            Intent i = new Intent(this, ListMuscles.class);
            startActivity(i);
        });
    }

}