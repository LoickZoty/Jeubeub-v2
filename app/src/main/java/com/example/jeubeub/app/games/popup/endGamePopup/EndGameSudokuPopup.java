package com.example.jeubeub.app.games.popup.endGamePopup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.games.model.Sudoku;
import com.example.jeubeub.app.games.popup.EndGamePopup;

public class EndGameSudokuPopup extends EndGamePopup {

    public EndGameSudokuPopup(@NonNull Context context, Sudoku game) {
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
