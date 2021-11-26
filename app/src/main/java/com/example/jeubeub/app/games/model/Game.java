package com.example.jeubeub.app.games.model;

import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.MenuActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Game implements Serializable {
    public final static String JEUBEUB_API_GAME = MenuActivity.JEUBEUB_API+"/game";

    public final static int EQUAL = 0;
    public final static int WIN = 1;
    public final static int LOOSE = 2;

    public int gameId;
    public String actualPlayer;

    public boolean finishGame;
    public Map<String,Integer> playersRankingFinishGame;

    public Game(JSONObject json) throws JSONException {
        this.gameId = json.getInt("id");
        this.setData(json);
    }

    public abstract Class<?> getActivity();

    public String getAPIPath() {
        return JEUBEUB_API_GAME+"/"+this.getClass().getSimpleName().toLowerCase();
    }

    public void setData(JSONObject json) throws JSONException {
        this.actualPlayer = json.getString("actualPlayer");
        this.finishGame = json.getBoolean("finishGame");
        this.playersRankingFinishGame = new Gson().fromJson(json.getJSONObject("playersRankingFinishGame").toString(), new TypeToken<HashMap<String, Integer>>() {}.getType());
    }
}
