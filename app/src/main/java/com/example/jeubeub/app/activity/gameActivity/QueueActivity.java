package com.example.jeubeub.app.activity.gameActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.example.jeubeub.R;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.games.Game;
import com.example.jeubeub.app.games.Morpion;
import com.example.jeubeub.app.games.Sudoku;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class QueueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        onClickBtnQueueGame(Morpion.class, (Button)findViewById(R.id.btnQueueMorpion));
        onClickBtnQueueGame(Sudoku.class, (Button)findViewById(R.id.btnQueueSudoku));
    }
    
    private void onClickBtnQueueGame(Class<?> cls, Button button) {
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
                }, QueueActivity.this, Game.JEUBEUB_API_GAME+"/"+cls.getSimpleName().toLowerCase()+"/waitQueue?playerId=1", retryPolicy);
            }
        });
    }
}