package com.example.jeubeub.app.leaderboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;

import android.os.Bundle;
import android.widget.Button;

public class MorpionLeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion_leaderboard);

        Button nombre_victoire = findViewById(R.id.morpion_nombre_victoire);
        nombre_victoire.setOnClickListener(v -> showLeaderbord(nombre_victoire.getTag().toString()));

        Button nombre_defaite = findViewById(R.id.morpion_nombre_defaite);
        nombre_defaite.setOnClickListener(v -> showLeaderbord(nombre_defaite.getTag().toString()));
    }

    private void showLeaderbord(String id) {
        //TODO CONSTANTE
        LoginActivity.getLeaderboardsClient()
                .getLeaderboardIntent(id)
                .addOnSuccessListener(intent -> startActivityForResult(intent, 9004))
                .addOnFailureListener(e -> System.out.println(e.getMessage()));
    }

}