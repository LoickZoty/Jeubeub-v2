package com.example.jeubeub.app.games.popup.endGamePopup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.games.model.Sudoku;

import org.json.JSONException;

public class EndGamePopupSudoku extends EndGamePopup {

    public EndGamePopupSudoku(@NonNull Context context, Sudoku game) throws JSONException {
        super(context, game);
    }

    @Override
    public int getContentView() {
        return R.layout.popup_end_game_morpion;
    }

    @Override
    public void setView() throws JSONException {

    }
}
