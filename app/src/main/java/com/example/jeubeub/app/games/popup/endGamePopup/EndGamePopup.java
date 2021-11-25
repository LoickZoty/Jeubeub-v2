package com.example.jeubeub.app.games.popup.endGamePopup;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.MenuActivity;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.popup.Popup;

import org.json.JSONException;

public abstract class EndGamePopup extends Popup {
    protected Game game;

    public EndGamePopup(@NonNull Context context, Game game) throws JSONException {
        super(context);
        this.game = game;

        initializeOnClickReturnQueue();
    }

    private void initializeOnClickReturnQueue() {
        Button btnReturnQueue = (Button)findViewById(R.id.btnReturnQueueEndGame);
        btnReturnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Intent intent = new Intent(getContext(), MenuActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
