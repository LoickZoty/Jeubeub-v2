package com.example.jeubeub.game;

import com.example.jeubeub.game.Game;

import org.json.JSONException;
import org.json.JSONObject;

public class Morpion extends Game {

    public Morpion(JSONObject json) throws JSONException {
        super(json);
        System.out.println("On a crée une partie");
    }

    @Override
    public void graphicsRefreshment(JSONObject json) {

    }

}
