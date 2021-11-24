package com.example.jeubeub.app.games.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.games.popup.EndGamePopup;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class GameActivity extends AppCompatActivity {
    public final static int MAX_TIME_REQUEST_REFRESHMENT = 60000;

    protected Game game;
    protected View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getContentView());

        view = findViewById(android.R.id.content).getRootView();
        game = (Game)getIntent().getExtras().getSerializable("games");

        viewRefreshmentLoop();
    }

    public abstract int getContentView();

    public abstract EndGamePopup getEndGamePopup() throws JSONException;

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
                    Request.getRequest(new VolleyCallback() {
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
                        }
                    }, GameActivity.this, Game.JEUBEUB_API_GAME + "/" + game.gameId + "/waitRefreshment?playerId=" + LoginActivity.USER_TOKEN, retryPolicy);
                } else {
                    try {
                        EndGamePopup endGamePopup = getEndGamePopup();
                        endGamePopup.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }
    
    public abstract void onSuccessViewRefreshmentLoop(JSONObject json) throws JSONException;

    public abstract void onErrorViewRefreshmentLoop();
}
