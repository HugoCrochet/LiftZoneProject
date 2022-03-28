package com.example.liftzone;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftzone.DAO.DBHandler;

public class Consult extends AppCompatActivity {

    DBHandler db;
    LinearLayout ll ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        ll = (LinearLayout)findViewById(R.id.layout);
        db = new DBHandler(this);
        generateData();
    }

    public void generateData (){
        db.selectAll();
        String response;
        for(int i=0; i<db.selectAll().size();i++)
        {
            TextView t;
            t = new TextView(getApplicationContext());
            String value = db.selectAll().get(i).getW_name();
            t.setText(value);
            t.setId(i);
            ll.addView(t);
        }

    }
}
