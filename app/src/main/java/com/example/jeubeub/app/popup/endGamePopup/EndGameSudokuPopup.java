package com.example.jeubeub.app.popup.endGamePopup;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.game.Morpion;
import com.example.jeubeub.app.game.Sudoku;

import org.json.JSONException;

public class EndGameSudokuPopup extends EndGamePopup {

    public EndGameSudokuPopup(@NonNull Context context, Sudoku game) throws JSONException {
        super(context, game);
    }

    @Override
    public void setView() {
        this.setView();
    }

    @Override
    public int getContentView() {
        return R.layout.popup_end_game_morpion;
    }
}
