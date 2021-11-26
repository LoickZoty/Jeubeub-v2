package com.example.jeubeub.app.games.popup.endGamePopup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.MenuActivity;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.leaderboard.service.LeaderboardService;
import com.example.jeubeub.app.popup.AlertPopup;
import com.example.jeubeub.app.popup.Popup;

import org.json.JSONException;

public abstract class EndGamePopup extends AlertPopup {
    protected Game game;
    protected int statusRanking;

    public EndGamePopup(@NonNull Context context, Game game) throws JSONException {
        super(context);
        this.game = game;
        this.statusRanking = game.playersRankingFinishGame.get(LoginActivity.USER_TOKEN);

        setView();
        setLeaderbord();
    }

    public void setView() throws JSONException {
        btnAlert.setText("Retour");

        TextView titleText = (TextView)findViewById(R.id.titleTextEndGame);
        if (statusRanking == Game.EQUAL) titleText.setText("Egalité");
        else if (statusRanking == Game.WIN) titleText.setText("Bravo, tu as gagné");
        else titleText.setText("Tu as perdu");
    }

    public void setLeaderbord() {
        if (statusRanking == Game.EQUAL) ;
        else if(statusRanking == Game.WIN) LeaderboardService.submitNewScore(1,LeaderboardService.MORPION_VICTOIRE, getOwnerActivity());
        else if (statusRanking == Game.LOOSE) LeaderboardService.submitNewScore(1,LeaderboardService.MORPION_DEFAITE, getOwnerActivity());
    }

    protected void eventOnClickAlert() {
        Intent intent = new Intent(getContext(), MenuActivity.class);
        getContext().startActivity(intent);
    }
}
