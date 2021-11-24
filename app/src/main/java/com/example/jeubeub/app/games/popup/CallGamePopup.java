package com.example.jeubeub.app.games.popup;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.Popup;
import com.example.jeubeub.app.games.activity.QueueActivity;
import com.example.jeubeub.app.games.model.Game;

import org.json.JSONException;
import org.json.JSONObject;

public class CallGamePopup extends Popup {
    protected Context context;

    public CallGamePopup(@NonNull Context context, JSONObject json) throws JSONException {
        super(context);
        this.context = context;

        setView(json);
        initializeOnClickAccept(json);
        initializeOnClickDeny();
    }

    public void setView(JSONObject json) throws JSONException {
        TextView titleText = (TextView)findViewById(R.id.titleTextCallGame);
        titleText.setText("Votre amis "+json.getString("playerIdRequest")+" t'invite à jouer au "+json.getString("gameName"));
    }

    @Override
    public int getContentView() {
        return R.layout.popup_call_game;
    }

    private void initializeOnClickAccept(JSONObject json) {
        Button btnReturnQueue = (Button)findViewById(R.id.btnAcceptCallGame);
        btnReturnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                System.out.println("On accept !");
            }
        });
    }

    private void initializeOnClickDeny() {
        Button btnReturnQueue = (Button)findViewById(R.id.btnDenyCallGame);
        btnReturnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                System.out.println("On refuse !");
            }
        });
    }
}
