package com.example.jeubeub.app.popup;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;

import org.json.JSONException;

public abstract class AlertPopup extends Popup {
    protected Button btnAlert;

    public AlertPopup(@NonNull Context context) throws JSONException {
        super(context);

        btnAlert = findViewById(R.id.btnAlert);

        initializeOnClickAlert();
    }

    public void setView() throws JSONException {
        btnAlert.setText("OK");
    }

    protected abstract void eventOnClickAlert();

    private void initializeOnClickAlert() {
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                eventOnClickAlert();
            }
        });
    }
}
