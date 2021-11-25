package com.example.jeubeub.app.games.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.games.popup.endGamePopup.EndGamePopup;
import com.example.jeubeub.app.leaderboard.service.LeaderboardService;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class GameActivity extends AppCompatActivity {
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
        if (!game.finishGame) {
            Request.getRequest(new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject json) {
                    try {
                        System.out.println("Le refresh "+json);
                        game.setData(json);
                        onSuccessViewRefreshmentLoop(json);
                        viewRefreshmentLoop();
                    } catch (JSONException e) {
                        System.err.println(e);
                    }
                }

                @Override
                public void onError(Exception exception) {
                    System.err.println(exception);
                    onErrorViewRefreshmentLoop();
                }
            }, GameActivity.this, Game.JEUBEUB_API_GAME + "/" + game.gameId + "/waitRefreshment?playerId=" + LoginActivity.USER_TOKEN, Request.infiniteRetryPolicy);
        } else {
            try {
                EndGamePopup endGamePopup = getEndGamePopup();
                int status = game.playersRankingFinishGame.get(LoginActivity.USER_TOKEN);
                if(status == 1){
                    LeaderboardService.submitNewScore(1,LeaderboardService.MORPION_VICTOIRE,this);
                }else if (status == 2){
                    LeaderboardService.submitNewScore(1,LeaderboardService.MORPION_DEFAITE,this);
                }
                endGamePopup.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    
    public abstract void onSuccessViewRefreshmentLoop(JSONObject json) throws JSONException;

    public abstract void onErrorViewRefreshmentLoop();
}
