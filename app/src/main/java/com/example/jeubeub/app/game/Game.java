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
import com.example.jeubeub.app.api.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public abstract class Game implements Serializable {
    public final static String JEUBEUB_API_GAME = "http://192.168.1.35:8080/Jeubeub/api/v1/game";
    public int gameId;

    public Game(JSONObject json) throws JSONException {
        this.gameId = json.getInt("id");
    }

    public abstract Class<?> getActivity();

    public abstract void setGraphicsRefreshment(View view, JSONObject json) throws JSONException;

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
}
