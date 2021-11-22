package com.example.jeubeub.app.game;

import android.app.Activity;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.api.VolleyCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class Game implements Serializable {
    public final static String JEUBEUB_API_GAME = LoginActivity.JEUBEUB_API+"/game";

    public int gameId;
    public String actualPlayer;

    public boolean finishGame;
    public Map<String,Integer> playersRankingFinishGame;

    public Game(JSONObject json) throws JSONException {
        this.gameId = json.getInt("id");
        this.setData(json);
    }

    public static void getRequest(final VolleyCallback callback, Activity activity, String url, DefaultRetryPolicy retryPolicy) {
                                                                System.out.println(url);
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    callback.onSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError(error);
                }
            });

        if (retryPolicy != null) request.setRetryPolicy(retryPolicy);
        queue.add(request);
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
