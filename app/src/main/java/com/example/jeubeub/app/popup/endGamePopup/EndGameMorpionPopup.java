package com.example.jeubeub.app.popup.endGamePopup;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.game.Morpion;

public class EndGameMorpionPopup extends EndGamePopup {

    public EndGameMorpionPopup(@NonNull Context context, Morpion game) {
        super(context, game);
        System.out.println("morpionendgame");
    }

    @Override
    public void setView() {
        super.setView();

        TextView titleText = (TextView)findViewById(R.id.titleTextEndGame);
        if (game.playersRankingFinishGame.get(0) == LoginActivity.USER_TOKEN) {
            titleText.setText("Bravo, tu as gagné");
        } else {
            titleText.setText("Tu as perdu, petite merde");
        }
    }

    @Override
    public int getContentView() {
        return R.layout.popup_end_game_morpion;
    }
}
