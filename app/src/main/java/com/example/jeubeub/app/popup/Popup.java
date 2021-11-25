package com.example.jeubeub.app.popup;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONException;

public abstract class Popup extends Dialog {
    public Popup(@NonNull Context context) throws JSONException {
        super(context);

        setContentView(getContentView());
    }

    public abstract int getContentView();

    public abstract void setView() throws JSONException;
}
