package com.example.liftzone;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Exercise  extends AppCompatActivity {

    ImageView imageExo;
    LinearLayout exerciseImageLayoutOne;
    LinearLayout exerciseImageLayoutTwo;
    LinearLayout exerciseImageLayoutThree;
    Button addExercise;
    String idExo;
    TextView nameExercise;
    ArrayList<String> image;
    int idExoBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_exercise);
        exerciseImageLayoutOne = findViewById(R.id.divOne);
        exerciseImageLayoutTwo = findViewById(R.id.divTwo);
        exerciseImageLayoutThree = findViewById(R.id.divThree);

        Intent i = getIntent();
        idExo = i.getStringExtra("idExoBase");
        idExoBase = Integer.parseInt(idExo);

        nameExercise = findViewById(R.id.nameExercise);
        nameExercise.setText(i.getStringExtra("nomExo"));

        addExercise = findViewById(R.id.addExercise);
        addExercise.setText("Ajouter cet exercice");
        addExercise.setBackgroundColor(Color.BLACK);
        addExercise.setTextColor(Color.WHITE);

        addExercise.setOnClickListener(view -> {
            Toast.makeText(this, i.getStringExtra("nomExo") + " ajouté à votre entrainement", Toast.LENGTH_LONG).show();
        });

        Exercise.RequestTask r = new Exercise.RequestTask();
        try {
            image = r.execute(idExoBase).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RequestTask extends AsyncTask<Integer, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {
            return requestHandler(integers[0]);
        }

        private ArrayList<String> requestHandler(int idExoBase) {
            ArrayList<String> response = null;
            String jsonText = "";
            try {
                URL url;
                url = new URL("https://wger.de/api/v2/exerciseimage/?exercise_base="+ String.valueOf(idExoBase) +"&format=json");
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
                    response.add(result.getString("image"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            try{
                generateImage(strings.get(0), exerciseImageLayoutThree);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        private void generateImage(String imageURL, LinearLayout layout) {
            imageExo = findViewById(R.id.imageExo);
            System.out.println(imageURL);
            Picasso.get()
                    .load(imageURL)
                    .into(imageExo);
        }
    }
}