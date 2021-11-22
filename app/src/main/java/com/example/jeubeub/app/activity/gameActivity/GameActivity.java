package com.example.jeubeub.app.activity.gameActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.game.Morpion;
import com.example.jeubeub.app.popup.endGamePopup.EndGamePopup;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public abstract class GameActivity extends AppCompatActivity {
    public final static int MAX_TIME_REQUEST_REFRESHMENT = 60000;

    protected Game game;
    protected View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getContentView());

        view = findViewById(android.R.id.content).getRootView();
        game = (Game)getIntent().getExtras().getSerializable("game");

        viewRefreshmentLoop();
    }

    public abstract int getContentView();

    public abstract EndGamePopup getEndGamePopup();

    public void viewRefreshmentLoop() {
        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(
                MAX_TIME_REQUEST_REFRESHMENT,
                1,
                1f
        );

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!game.finishGame) {
                    Game.getRequest(new VolleyCallback() {
                        @Override
                        public void onSuccess(JSONObject json) {
                            try {
                                System.out.println("Le refresh "+json);
                                game.setData(json);
                                onSuccessViewRefreshmentLoop(json);
                                run();
                            } catch (JSONException e) {
                                System.err.println(e);
                            }
                        }

                        @Override
                        public void onError(Exception exception) {
                            System.err.println(exception);
                            onErrorViewRefreshmentLoop();
                            run();
                        }
                    }, GameActivity.this, Game.JEUBEUB_API_GAME + "/" + game.gameId + "/waitRefreshment?playerId=" + LoginActivity.USER_TOKEN, retryPolicy);
                } else {
                    EndGamePopup endGamePopup = getEndGamePopup();
                    endGamePopup.show();
                }
            }
        });
        t1.start();
    }
    
    public abstract void onSuccessViewRefreshmentLoop(JSONObject json) throws JSONException;

    public abstract void onErrorViewRefreshmentLoop();
}
