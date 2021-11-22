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
import com.example.jeubeub.app.popup.endGamePopup.EndGameMorpionPopup;
import com.example.jeubeub.app.popup.endGamePopup.EndGamePopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MorpionActivity extends GameActivity {
    private Morpion morpion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        morpion = (Morpion)game;

        initializeOnClickMap();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_morpion;
    }

    @Override
    public EndGamePopup getEndGamePopup() {
        return new EndGameMorpionPopup(MorpionActivity.this, morpion);
    }

    public int getDrawableByMarker(int marker) {
        if (marker == Morpion.X) return R.drawable.croix;
        else if (marker == Morpion.O) return R.drawable.rond;
        else return R.drawable.empty;
    }

    @Override
    public void onSuccessViewRefreshmentLoop(JSONObject json) throws JSONException {
        setGraphicsMap(json);
    }

    @Override
    public void onErrorViewRefreshmentLoop() {

    }

    private void setGraphicsMap(JSONObject json) throws JSONException {
        TableLayout morpionTable = (TableLayout)view.findViewById(R.id.morpionTable);
        JSONArray map = json.getJSONObject("actualGameData").getJSONArray("map");
        for (int i = 0; i < morpionTable.getChildCount(); i++) {
            TableRow tableRow = (TableRow)morpionTable.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                ImageView imageView = (ImageView)tableRow.getChildAt(j);
                imageView.setImageResource(getDrawableByMarker(map.getJSONArray(i).getInt(j)));
            }
        }
    }

    public void initializeOnClickMap() {
        TableLayout morpionTable = (TableLayout)view.findViewById(R.id.morpionTable);
        for (int i = 0; i < morpionTable.getChildCount(); i++) {
            final int x = i;
            TableRow tableRow = (TableRow)morpionTable.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                final int y = j;
                tableRow.getChildAt(j).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (morpion.actualPlayer == LoginActivity.USER_TOKEN) {
                            ((ImageView)view).setImageResource(getDrawableByMarker(morpion.marker)); //Permet de simuler une mise à jour (ça evite les lags)
                            Game.getRequest(new VolleyCallback() {
                                @Override
                                public void onSuccess(JSONObject json) {
                                    System.err.println(json);
                                }

                                @Override
                                public void onError(Exception exception) {
                                    System.err.println(exception);
                                }
                            }, MorpionActivity.this, game.getAPIPath() + "/" + game.gameId + "/dropMarker?playerId=" + LoginActivity.USER_TOKEN + "&x=" + x + "&y=" + y, null);
                        }
                    }
                });
            }
        }
    }
}