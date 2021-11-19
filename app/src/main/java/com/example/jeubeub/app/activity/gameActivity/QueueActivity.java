package com.example.jeubeub.app.activity.gameActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.example.jeubeub.R;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.game.Morpion;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class QueueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        onClickBtnQueueGame(Morpion.class, (Button)findViewById(R.id.btnQueueMorpion));
    }
    
    private void onClickBtnQueueGame(Class<?> cls, Button button) {
        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(
                300000,
                1,
                1f
        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.getRequest(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject json) {
                        try {
                            Game game = (Game) cls.getConstructor(JSONObject.class).newInstance(json);
                            Intent intent = new Intent(QueueActivity.this, game.getActivity());
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
                }, QueueActivity.this, Game.JEUBEUB_API_GAME+"/"+cls.getSimpleName().toLowerCase()+"/waitQueue?playerId=1", null);
            }
        });
    }
}