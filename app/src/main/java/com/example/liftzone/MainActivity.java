package com.example.liftzone;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftzone.DAO.DBHandler;

public class MainActivity extends AppCompatActivity {

    Button addWorkout;
    DBHandler db;
    LinearLayout ll ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addWorkout = findViewById(R.id.addWorkout);
        ll = (LinearLayout)findViewById(R.id.layoutWorkout);
        db = new DBHandler(this);
        generateData();

        addWorkout.setOnClickListener(view -> {
            Intent i = new Intent(this, AddWorkout.class);
            startActivity(i);
        });
    }

    public void generateData (){
        db.selectAll();
        String response;
        for(int i=0; i<db.selectAll().size();i++)
        {
            Button b;
            b = new Button(getApplicationContext());
            String value = db.selectAll().get(i).getW_name();
            b.setText(value);
            b.setId(i);
            b.setBackgroundColor(Color.BLACK);
            b.setTextColor(Color.WHITE);
            ll.addView(b);
        }

    }
}