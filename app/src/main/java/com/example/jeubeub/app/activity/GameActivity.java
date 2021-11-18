package com.example.jeubeub.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jeubeub.R;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.game.Morpion;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class GameActivity extends AppCompatActivity {
    public final static int MAX_TIME_REQUEST_REFRESHMENT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        onClickBtnQueueGame(Morpion.class, (Button)findViewById(R.id.btnQueueMorpion));
    }
    
    private void onClickBtnQueueGame(Class<?> cls, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.getRequest(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject json) {
                        try {
                            Game game = (Game) cls.getConstructor(JSONObject.class).newInstance(json);
                            Intent intent = new Intent(GameActivity.this, game.getActivity());
                            intent.putExtra("game", game);
                            startActivity(intent);
                        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                            System.err.println("onSuccess catch : " + e);
                        }
                    }

                    @Override
                    public void onError(Exception exception) {
                        System.err.println(exception);
                    }
                },GameActivity.this, Game.JEUBEUB_API_GAME+"/"+cls.getSimpleName().toLowerCase()+"/waitQueue?playerId=1", null);
            }
        });
    }
}