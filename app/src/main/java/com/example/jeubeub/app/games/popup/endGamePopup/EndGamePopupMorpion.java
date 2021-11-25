package com.example.jeubeub.app.games.popup.endGamePopup;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.games.model.Morpion;

import org.json.JSONException;

public class EndGamePopupMorpion extends EndGamePopup {
    public final static int EQUAL = 0;
    public final static int WIN = 1;
    public final static int LOOSE = 2;

    public EndGamePopupMorpion(@NonNull Context context, Morpion game) throws JSONException {
        super(context, game);
    }

    public void setView() throws JSONException {
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
