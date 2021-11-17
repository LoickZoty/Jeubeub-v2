package com.example.jeubeub.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jeubeub.R;


public class MorpionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion);
        System.out.println("MORPION");
    }
}