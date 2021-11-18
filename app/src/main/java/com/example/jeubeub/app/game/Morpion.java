package com.example.jeubeub.app.game;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.activity.MorpionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Morpion extends Game {
    public final static String JEUBEUB_API_MORPION = Game.JEUBEUB_API_GAME+"/morpion";
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
            if (players.getInt(i) == LoginActivity.token) return i;
        }
        return -1;
    }

    @Override
    public Class<?> getActivity() {
        return MorpionActivity.class;
    }

    @Override
    public void setGraphicsRefreshment(View view, JSONObject json) throws JSONException {
        setGraphicsMap(view, json);
    }

    private void setGraphicsMap(View view, JSONObject json) throws JSONException {
        TableLayout morpionTable = (TableLayout)view.findViewById(R.id.morpionTable);
        JSONArray map = json.getJSONObject("actualGameData").getJSONArray("map");
        System.out.println("La map : "+map);
        for (int i = 0; i < morpionTable.getChildCount(); i++) {
            TableRow tableRow = (TableRow)morpionTable.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                ImageView imageView = (ImageView)tableRow.getChildAt(j);
                int cell = map.getJSONArray(i).getInt(j);
                if (cell == X) {
                    imageView.setImageResource(R.drawable.croix);
                }
                else if (cell == O) {
                    imageView.setImageResource(R.drawable.rond);
                }
                else {
                    imageView.setImageResource(android.R.color.transparent);
                }
            }
        }
    }
}
