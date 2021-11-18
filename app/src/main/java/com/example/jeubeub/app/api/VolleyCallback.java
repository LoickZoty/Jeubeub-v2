package com.example.jeubeub.app.api;

import org.json.JSONObject;

public interface VolleyCallback {
     public void onSuccess(JSONObject json);
     public void onError(Exception exception);
}
