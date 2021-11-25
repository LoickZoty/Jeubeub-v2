package com.example.jeubeub.app.api;

import android.content.Context;
import android.content.Intent;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.MenuActivity;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.games.popup.CallGameReceivPopup;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class Request {
    public final static DefaultRetryPolicy infiniteRetryPolicy = new DefaultRetryPolicy(Integer.MAX_VALUE, 1, 1);

    public static void getRequest(final VolleyCallback callback, Context context, String url, DefaultRetryPolicy retryPolicy) {
        System.out.println(url);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (callback != null) callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callback != null) callback.onError(error);
            }
        });

        if (retryPolicy != null) request.setRetryPolicy(retryPolicy);
        queue.add(request);
    }

    public static void sendWaitQueueRequest(Context context, Class<?> cls, Integer queueId) {
        String callMethod;
        if (queueId != null) callMethod = "waitPrivateQueue?playerId="+LoginActivity.USER_TOKEN+"&queueId="+queueId;
        else callMethod = "waitPublicQueue?playerId="+LoginActivity.USER_TOKEN;

        Request.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    System.out.println("La file jeu : "+json);
                    Game game = (Game) cls.getConstructor(JSONObject.class).newInstance(json);
                    Intent intent = new Intent(context, game.getActivity());
                    intent.putExtra("games", game);
                    context.startActivity(intent);
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    System.err.println("onSuccess catch : " + e);
                }
            }

            @Override
            public void onError(Exception exception) {
                System.err.println(exception);
            }
        }, context, Game.JEUBEUB_API_GAME+"/"+cls.getSimpleName().toLowerCase()+"/"+callMethod, infiniteRetryPolicy);
    }

    public static void sendWaitCallGameRequest(Context context) {
        Request.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    CallGameReceivPopup callGameReceivPopup = new CallGameReceivPopup(context, json);
                    callGameReceivPopup.show();
                    sendWaitCallGameRequest(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception exception) {
                System.err.println(exception);
            }
        }, context, MenuActivity.JEUBEUB_API + "/friend/waitCallGame?playerId=" + LoginActivity.USER_TOKEN, infiniteRetryPolicy);
    }
}
