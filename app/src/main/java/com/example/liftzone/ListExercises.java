package com.example.liftzone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListExercises extends AppCompatActivity  {

    LinearLayout exerciseLayout;
    ArrayList<String> exercises;
    String muscle;
    int idMuscle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_exercises);
        exerciseLayout = findViewById(R.id.listExerciseIn);

        Intent i = getIntent();
        muscle = i.getStringExtra("idMuscle");
        idMuscle = Integer.parseInt(muscle);
        ListExercises.RequestTask r = new ListExercises.RequestTask();
        try {
            exercises = r.execute(idMuscle).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RequestTask extends AsyncTask<Integer, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {
            return requestHandler(integers[0]);
        }

        private ArrayList<String> requestHandler(int idMuscle) {
            ArrayList<String> response = null;
            String jsonText = "";
            try {
                URL url;
                url = new URL("https://wger.de/api/v2/exercise/?format=json&language=2&limit=40&muscles="+String.valueOf(idMuscle));
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
                    response.add(result.getString("exercise_base"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            for(int i = 0; i < strings.size()/3; i++) {
                String exercise = strings.get(3*i+1);
                generateButtonExercise(exercise, 3*i+2, exerciseLayout);
            }
        }

        private void generateButtonExercise (String exercise, int index, LinearLayout layout) {
            Button b = new Button(getApplicationContext());
            b.setText(exercise);
            b.setId(index);
            layout.addView(b);
            b.setOnClickListener(view -> {
                Intent in = new Intent(ListExercises.this, Exercise.class);
                in.putExtra("idExoBase",exercises.get(index));
                in.putExtra("nomExo",exercises.get(index-1));
                startActivity(in);
            });
        }
    }
}