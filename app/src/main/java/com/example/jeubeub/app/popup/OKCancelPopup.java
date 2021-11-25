package com.example.jeubeub.app.popup;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.api.Request;

import org.json.JSONException;

public abstract class OKCancelPopup extends Popup {
    private Button btnOK;
    private Button btnCancel;

    public OKCancelPopup(@NonNull Context context) throws JSONException {
        super(context);

        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);

        initializeOnClickOK();
        initializeOnClickCancel();
    }

    public void setView() throws JSONException {
        btnOK.setText("OK");
        btnCancel.setText("Annuler");
    }

    protected abstract void eventOnClickOK();

    protected abstract void eventOnClickCancel();

    private void initializeOnClickOK() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                eventOnClickOK();
            }
        });
    }

    private void initializeOnClickCancel() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                eventOnClickCancel();
            }
        });
    }
}
