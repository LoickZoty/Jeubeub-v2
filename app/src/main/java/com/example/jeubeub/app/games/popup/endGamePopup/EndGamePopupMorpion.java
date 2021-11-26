package com.example.jeubeub.app.games.popup.endGamePopup;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.games.model.Morpion;

import org.json.JSONException;

public class EndGamePopupMorpion extends EndGamePopup {
    public EndGamePopupMorpion(@NonNull Context context, Morpion game) throws JSONException {
        super(context, game);
    }

    public void setView() throws JSONException {
        super.setView();
    }

    @Override
    public int getContentView() {
        return R.layout.popup_end_game_morpion;
    }
}
