package com.example.jeubeub.app.popup;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.api.Request;

import org.json.JSONException;

public abstract class ConfirmPopup extends Popup {
    protected Button btnConfirmOK;
    protected Button btnConfirmCancel;

    public ConfirmPopup(@NonNull Context context) throws JSONException {
        super(context);

        btnConfirmOK = findViewById(R.id.btnConfirmOK);
        btnConfirmCancel = findViewById(R.id.btnConfirmCancel);

        initializeOnClickOK();
        initializeOnClickCancel();
    }

    public void setView() throws JSONException {
        btnConfirmOK.setText("OK");
        btnConfirmCancel.setText("Annuler");
    }

    protected abstract void eventOnClickOK();

    protected abstract void eventOnClickCancel();

    private void initializeOnClickOK() {
        btnConfirmOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                eventOnClickOK();
            }
        });
    }

    private void initializeOnClickCancel() {
        btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                eventOnClickCancel();
            }
        });
    }
}
