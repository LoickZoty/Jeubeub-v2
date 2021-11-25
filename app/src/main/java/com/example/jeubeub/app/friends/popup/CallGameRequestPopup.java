package com.example.jeubeub.app.friends.popup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.popup.OKCancelPopup;
import com.example.jeubeub.app.popup.Popup;

import org.json.JSONException;

public class CallGameRequestPopup extends OKCancelPopup {

    public CallGameRequestPopup(@NonNull Context context) throws JSONException {
        super(context);
    }

    @Override
    protected void eventOnClickOK() {

    }

    @Override
    protected void eventOnClickCancel() {

    }

    @Override
    public int getContentView() {
        return R.layout.popup_call_game_request;
    }

    @Override
    public void setView() throws JSONException {

    }
}
