package com.example.jeubeub.app.game;

import com.example.jeubeub.app.activity.MorpionActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Morpion extends Game {

    public Morpion(JSONObject json) throws JSONException {
        super(json);
        System.out.println("On a crée une partie");
    }

    @Override
    public Class<?> getActivity() {
        return MorpionActivity.class;
    }

    @Override
    public void graphicsRefreshment(JSONObject json) {

    }

}
