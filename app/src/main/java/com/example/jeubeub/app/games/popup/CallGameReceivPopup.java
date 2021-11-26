package com.example.jeubeub.app.games.popup;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.popup.ConfirmPopup;
import com.example.jeubeub.app.api.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class CallGameReceivPopup extends ConfirmPopup {
    protected int playerIdRequest;
    protected int queueId;
    protected String gameName;

    public CallGameReceivPopup(@NonNull Context context, JSONObject json) throws JSONException {
        super(context);

        this.playerIdRequest = json.getInt("playerIdRequest");
        this.queueId = json.getInt("queueId");
        this.gameName = json.getString("gameName");

        setView();
    }

    @Override
    public void setView() throws JSONException {
        super.setView();
        TextView titleText = (TextView)findViewById(R.id.titleTextCallGame);
        titleText.setText("Ton amis "+playerIdRequest+" t'invite à jouer au "+gameName);
    }

    @Override
    public int getContentView() {
        return R.layout.popup_call_game_receiv;
    }

    @Override
    protected void eventOnClickOK() {
        try {
            Request.sendWaitQueueRequest(getContext(), Class.forName("com.example.jeubeub.app.games.model."+gameName), queueId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void eventOnClickCancel() {

    }
}
