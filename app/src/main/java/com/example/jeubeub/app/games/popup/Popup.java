package com.example.jeubeub.app.games.popup;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public abstract class Popup extends Dialog {
    public Popup(@NonNull Context context) {
        super(context);
        setContentView(getContentView());
    }

    public abstract int getContentView();
}
