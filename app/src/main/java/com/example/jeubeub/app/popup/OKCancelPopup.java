package com.example.jeubeub.app.popup;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.api.Request;

import org.json.JSONException;

public abstract class OKCancelPopup extends Popup {
    public OKCancelPopup(@NonNull Context context) throws JSONException {
        super(context);

        initializeOnClickOK();
        initializeOnClickCancel();
    }

    public void setView() throws JSONException {
        ((Button)findViewById(R.id.btnOK)).setText("OK");
        ((Button)findViewById(R.id.btnCancel)).setText("Annuler");
    }

    protected abstract void eventOnClickOK();

    protected abstract void eventOnClickCancel();

    private void initializeOnClickOK() {
        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                eventOnClickOK();
            }
        });
    }

    private void initializeOnClickCancel() {
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                eventOnClickCancel();
            }
        });
    }
}
