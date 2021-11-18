package com.example.jeubeub.app.game;

import android.app.Activity;
import android.os.Parcelable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import callAPI.VolleyCallback;

public abstract class Game implements Serializable {
    protected int gameId;

    public Game(JSONObject json) throws JSONException {
        this.gameId = json.getInt("id");
    }

    public abstract Class<?> getActivity();

    public abstract void graphicsRefreshment(JSONObject json);

    public static void getRequest(final VolleyCallback callback, Activity activity, String url) {
                                                                System.out.println(url);
        RequestQueue queue = Volley.newRequestQueue(activity);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        callback.onSuccess(new JSONObject(response));
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError(error);
                }
            });
        queue.add(stringRequest);
    }
}
