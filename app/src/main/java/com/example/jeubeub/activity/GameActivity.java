package com.example.jeubeub.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jeubeub.game.Game;
import com.example.jeubeub.game.Morpion;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import com.example.jeubeub.R;

import callAPI.VolleyCallback;

public class GameActivity extends AppCompatActivity {
    public final static String JEUBEUB_API = "http://192.168.1.35:8080/Jeubeub/api/v1";

    private Game game = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        onClickBtnQueueGame(Morpion.class, findViewById(R.id.btnQueueMorpion));
    }
    
    private void onClickBtnQueueGame(Class<?> cls, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Game.getRequest(new VolleyCallback() {
                   @Override
                   public void onSuccess(JSONObject json) {
                       try {
                           game = (Game)cls.getDeclaredConstructor().newInstance(json);
                       } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                           System.err.println("onSuccess catch : "+e);
                       }
                   }

                   @Override
                   public void onError(Exception exception) {
                       System.err.println(exception);
                   }
               }, GameActivity.this, JEUBEUB_API+"/game/"+cls.getSimpleName().toLowerCase()+"/waitQueue?playerId=1");
            }
        });
    } 
}
