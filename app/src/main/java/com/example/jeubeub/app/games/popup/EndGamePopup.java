package com.example.jeubeub.app.games.popup;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.games.activity.QueueActivity;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.Popup;

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
