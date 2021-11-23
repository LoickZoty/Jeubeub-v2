package com.example.jeubeub.app.games;

import com.example.jeubeub.app.activity.gameActivity.SudokuActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Sudoku extends Game {
    public Sudoku(JSONObject json) throws JSONException {
        super(json);
    }

    @Override
    public Class<?> getActivity() {
        return SudokuActivity.class;
    }

    @Override
    public void setData(JSONObject json) throws JSONException {
        super.setData(json);
    }
}
