package com.example.jeubeub.app.activity.gameActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.game.Morpion;
import com.example.jeubeub.app.game.Sudoku;
import com.example.jeubeub.app.popup.endGamePopup.EndGameMorpionPopup;
import com.example.jeubeub.app.popup.endGamePopup.EndGamePopup;
import com.example.jeubeub.app.popup.endGamePopup.EndGameSudokuPopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SudokuActivity extends GameActivity {
    private Sudoku sudoku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sudoku = (Sudoku)game;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_sudoku;
    }

    @Override
    public EndGamePopup getEndGamePopup() throws JSONException {
        return new EndGameSudokuPopup(SudokuActivity.this, sudoku);
    }

    @Override
    public void onSuccessViewRefreshmentLoop(JSONObject json) throws JSONException {
    }

    @Override
    public void onErrorViewRefreshmentLoop() {

    }
}