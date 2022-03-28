package com.example.liftzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftzone.DAO.DBHandler;

public class AddWorkout extends AppCompatActivity {

    DBHandler db;
    Button searchMuscles;
    Button goBDD;
    private EditText w_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_workout);

        w_name = (EditText) findViewById(R.id.workout_name);
        searchMuscles = findViewById(R.id.search_button);
        goBDD = findViewById(R.id.bdd);
        db = new DBHandler(this);

        searchMuscles.setOnClickListener(view -> {
            //Intent i = new Intent(this, ListMuscles.class);
            String name = w_name.getText().toString();
            db.insertWorkout(name);
            Toast.makeText(this, "Entrainement ajouté à la collection" , Toast.LENGTH_LONG).show();
            //startActivity(i);
        });


        goBDD.setOnClickListener(view -> {
            Intent i = new Intent(this, Consult.class);
            startActivity(i);
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }



}