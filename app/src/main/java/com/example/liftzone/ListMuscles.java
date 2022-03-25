package com.example.liftzone;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListMuscles extends AppCompatActivity {

    LinearLayout muscleLayout;
    ArrayList<String> muscles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_muscles);
        muscleLayout = findViewById(R.id.listMuscleIn);

        Intent i = getIntent();
        ListMuscles.RequestTask r = new ListMuscles.RequestTask();
        try {
            muscles = r.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RequestTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            return requestHandler();
        }

        private ArrayList<String> requestHandler() {
            ArrayList<String> response = null;
            String jsonText = "";
            try {
                URL url;
                url = new URL("https://wger.de/api/v2/muscle/?format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne = bufferedReader.readLine() ;
                while (ligne != null){
                    jsonText += ligne;
                    ligne = bufferedReader.readLine();
                }
                JSONObject toDecode = new JSONObject(jsonText);
                response = decodeJSON(toDecode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        private ArrayList<String> decodeJSON(JSONObject toDecode) {
            ArrayList<String> response = new ArrayList<>();
            try {
                JSONArray results = toDecode.getJSONArray("results");
                for(int i = 0; i < results.length(); i++) {
                    JSONObject result = results.getJSONObject(i);
                    response.add(result.getString("id"));
                    response.add(result.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            for(int i = 0; i < strings.size()/2; i++) {
                String exercise = strings.get(2*i+1);
                generateButtonMuscle(exercise, 2*i, muscleLayout);
            }
        }

        private void generateButtonMuscle (String muscle, int index, LinearLayout layout){
            Button b = new Button(getApplicationContext());
            b.setText(muscle);
            b.setId(index);
            layout.addView(b);
            b.setOnClickListener(view -> {
                Intent in = new Intent(ListMuscles.this, ListExercises.class);
                in.putExtra("idMuscle",muscles.get(index));
                startActivity(in);
            });
        }
    }
}
