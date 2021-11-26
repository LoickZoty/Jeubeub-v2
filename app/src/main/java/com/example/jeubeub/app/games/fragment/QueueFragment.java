package com.example.jeubeub.app.games.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.MenuActivity;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.friends.popup.CallGameRequestPopup;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.games.model.Morpion;
import com.example.jeubeub.app.games.model.Sudoku;
import com.example.jeubeub.app.games.popup.endGamePopup.EndGamePopup;

import org.json.JSONException;
import org.json.JSONObject;

public class QueueFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue, container, false);

        onClickBtnQueueGamePublic(Morpion.class, view.findViewById(R.id.btnQueueMorpionPublic));
        onClickBtnQueueGamePublic(Sudoku.class, view.findViewById(R.id.btnQueueSudokuPublic));

        onClickBtnQueueGamePrivate(Morpion.class, view.findViewById(R.id.btnQueueMorpionPrivate));

        return view;
    }

    private void onClickBtnQueueGamePublic(Class<?> cls, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request.sendWaitQueueRequest(getContext(), cls, null);
            }
        });
    }

    private void onClickBtnQueueGamePrivate(Class<?> cls, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CallGameRequestPopup callGameRequestPopup = new CallGameRequestPopup(getContext(), cls);
                    callGameRequestPopup.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
