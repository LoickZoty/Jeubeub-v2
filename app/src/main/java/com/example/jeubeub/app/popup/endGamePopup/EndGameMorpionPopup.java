package com.example.jeubeub.app.popup.endGamePopup;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.game.Morpion;

import org.json.JSONException;

public class EndGameMorpionPopup extends EndGamePopup {
    public final static int EQUAL = 0;
    public final static int WIN = 1;
    public final static int LOOSE = 2;

    public EndGameMorpionPopup(@NonNull Context context, Morpion game) {
        super(context, game);
        System.out.println("morpionendgame");
    }

    @Override
    public void setView() {
        super.setView();

        TextView titleText = (TextView)findViewById(R.id.titleTextEndGame);
        System.out.println(game.playersRankingFinishGame);
        int status = game.playersRankingFinishGame.get(LoginActivity.USER_TOKEN);
        if (status == EQUAL) {
            titleText.setText("Egalité");
        } else if (status == WIN) {
            titleText.setText("Bravo, tu as gagné");
        } else {
            titleText.setText("Tu as perdu");
        }
    }

    @Override
    public int getContentView() {
        return R.layout.popup_end_game_morpion;
    }
}
