package com.example.jeubeub.app.games.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.games.model.Morpion;
import com.example.jeubeub.app.games.model.Sudoku;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class QueueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Yes???????");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        onClickBtnQueueGamePublic(Morpion.class, (Button)findViewById(R.id.btnQueueMorpionPublic));
        onClickBtnQueueGamePublic(Sudoku.class, (Button)findViewById(R.id.btnQueueSudokuPublic));
    }
    
    private void onClickBtnQueueGamePublic(Class<?> cls, Button button) {
        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(
                300000,
                1,
                1
        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request.getRequest(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject json) {
                        try {
                            System.out.println("La file jeu : "+json);
                            Game game = (Game) cls.getConstructor(JSONObject.class).newInstance(json);
                            Intent intent = new Intent(QueueActivity.this, game.getActivity());
                            intent.putExtra("games", game);
                            startActivity(intent);
                        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                            System.err.println("onSuccess catch : " + e);
                        }
                    }

                    @Override
                    public void onError(Exception exception) {
                        System.err.println(exception);
                    }
                }, QueueActivity.this, Game.JEUBEUB_API_GAME+"/"+cls.getSimpleName().toLowerCase()+"/waitQueue?playerId="+LoginActivity.USER_TOKEN, retryPolicy);
            }
        });
    }
}