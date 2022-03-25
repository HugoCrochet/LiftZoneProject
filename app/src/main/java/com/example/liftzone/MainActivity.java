package com.example.liftzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addWorkout = findViewById(R.id.addWorkout);

        addWorkout.setOnClickListener(view -> {
            Intent i = new Intent(this, AddWorkout.class);
            startActivity(i);
        });
    }
}