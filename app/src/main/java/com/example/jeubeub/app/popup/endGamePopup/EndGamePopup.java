package com.example.jeubeub.app.popup.endGamePopup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.gameActivity.QueueActivity;
import com.example.jeubeub.app.game.Game;
import com.example.jeubeub.app.popup.Popup;

import org.json.JSONException;

public abstract class EndGamePopup extends Popup {
    protected Context context;
    protected Game game;

    public EndGamePopup(@NonNull Context context, Game game) {
        super(context);
        this.context = context;
        this.game = game;

        initializeOnClickReturnQueue();
        setView();
    }

    public void setView() {

    }

    private void initializeOnClickReturnQueue() {
        Button btnReturnQueue = (Button)findViewById(R.id.btnReturnQueueEndGame);
        btnReturnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Intent intent = new Intent(context, QueueActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
