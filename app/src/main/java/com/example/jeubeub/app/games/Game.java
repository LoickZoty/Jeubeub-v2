package com.example.jeubeub.app.games;

import com.example.jeubeub.app.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Game implements Serializable {
    public final static String JEUBEUB_API_GAME = LoginActivity.JEUBEUB_API+"/games";

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
