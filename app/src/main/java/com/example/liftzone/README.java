package com.example.liftzone;

public class README {
    /*
    -Lancement de l'app : La classe HomePage se lance avec une animation qui recrute le layout activity_home_page et up et down du dossier anim avec
        logo.png et splash1.jpg sont les images du logo et du fond de l'écran animé
    -Ensuite se lance automatiquement (apres 3500ms) la MainActivity. Cette classe affiche tous les entrainements enregistrés en bdd en bouclant dessus.
    -A partir de la on peut ajouter un nouvel entrainement en bdd avec AddWorkout, on ira chercher les exos avec l'api en faisant des requetes par muscles
    -On peut consulter les entrainements et voir les differents exercices qui les composent.
    -La classe DBContract défini la structure des tables de la base
    -Un entrainement possede un nom, une duree, et des exercices.
    -Un exercice possede un nom, une description et une image.
    -On peut supprimer un entrainement (et le modifier).

/*
        startButton = findViewById(R.id.search_button);
        muscleInput = findViewById(R.id.muscle_input);

        startButton.setOnClickListener(view -> {
            int idMuscle = 0;
            try {
                idMuscle = Integer.parseInt(muscleInput.getText().toString());
            } catch(NumberFormatException e) {
                Log.d("startButton", "clicked: Pas de musclé entré ou idMuscle invalide");
            } finally {
                Intent i = new Intent(this, ListExercises.class);
                if(idMuscle==0){
                    Toast.makeText(this, "Muscle invalide", Toast.LENGTH_LONG).show();
                }
                else{
                    i.putExtra("idMuscle", idMuscle);
                    startActivity(i);
                }
            }
        });

         */



















}
