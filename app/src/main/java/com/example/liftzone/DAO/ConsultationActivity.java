package com.example.liftzone.DAO;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.liftzone.DAO.DBHandler;
import com.example.liftzone.R;

public class ConsultationActivity extends AppCompatActivity {
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
        /*
        for(int i=0; i<db.selectAll().size();i++)
        {
            TextView t;
            t = new TextView(getApplicationContext());
            String value = db.selectAll().get(i).getM_nom() +" ";
            value += db.selectAll().get(i).getM_genre() +" ";
            value += db.selectAll().get(i).getM_date() +" ";
            value += db.selectAll().get(i).getM_note() +" ";
            t.setText(value);
            t.setId(i);
            ll.addView(t);
        }
        */


    }
}