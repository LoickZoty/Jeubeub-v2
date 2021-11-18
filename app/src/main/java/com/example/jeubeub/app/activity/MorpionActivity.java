package com.example.jeubeub.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.jeubeub.R;
import com.example.jeubeub.app.game.Morpion;

public class MorpionActivity extends AppCompatActivity {
    private Morpion morpion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion);
        System.out.println("MORPION");

        //morpion = (Morpion)getIntent().getExtras().getSerializable("game");

        TableLayout morpionTable = (TableLayout)findViewById(R.id.morpionTable);
        System.out.println(morpionTable.getChildCount());
    }
}