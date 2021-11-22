package com.example.jeubeub.app.game;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.activity.gameActivity.MorpionActivity;
import com.example.jeubeub.app.popup.endGamePopup.EndGameMorpionPopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Morpion extends Game {
    public static final int X = 0;
    public static final int O = 1;
    public static final int E = 2;

    public int marker;

    public Morpion(JSONObject json) throws JSONException {
        super(json);
        this.marker = this.getPlayerMarker(json.getJSONArray("players"));
        System.out.println("On a crée une partie");
    }

    public int getPlayerMarker(JSONArray players) throws JSONException {
        for (int i = 0; i < players.length(); i++) {
            if (players.getString(i).equals(LoginActivity.USER_TOKEN)) return i;
        }
        return -1;
    }

    @Override
    public Class<?> getActivity() {
        return MorpionActivity.class;
    }

    @Override
    public void setData(JSONObject json) throws JSONException {
        super.setData(json);
    }
}
