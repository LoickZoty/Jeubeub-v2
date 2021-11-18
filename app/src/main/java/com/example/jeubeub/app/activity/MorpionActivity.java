package com.example.jeubeub.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.android.volley.DefaultRetryPolicy;
import com.example.jeubeub.R;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.game.Morpion;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class MorpionActivity extends AppCompatActivity {
    private Morpion morpion;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion);

        view = findViewById(android.R.id.content).getRootView();
        System.out.println("MORPION");

        //morpion = (Morpion)getIntent().getExtras().getSerializable("game");
        try {
            morpion = new Morpion(new JSONObject("{\"name\":\"Morpion\",\"id\":0,\"players\":[1,2],\"actualPlayer\":1,\"playersRankingFinishGame\":[],\"actualGameData\":{\"map\":[[2,0,0],[2,2,2],[2,2,2]]},\"finishGame\":false}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TableLayout morpionTable = (TableLayout)findViewById(R.id.morpionTable);
        System.out.println(morpionTable.getChildCount());

        viewRefreshmentLoop();
    }

    private void viewRefreshmentLoop() {
        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(
                GameActivity.MAX_TIME_REQUEST_REFRESHMENT,
                1,
                1f
        );

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Game.getRequest(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject json) {
                        try {
                            System.out.println(json);
                            morpion.setGraphicsRefreshment(view, json);
                            run();
                        } catch (JSONException e) {
                            System.err.println(e);
                        }
                    }

                    @Override
                    public void onError(Exception exception) {
                        System.err.println(exception);
                        run();
                    }
                }, MorpionActivity.this, Game.JEUBEUB_API_GAME+"/"+morpion.gameId+"/waitRefreshment?playerId="+LoginActivity.token, retryPolicy);
                System.out.println("ça enchaine ou pas ?????");
            }
        });
        t1.start();
    }
}