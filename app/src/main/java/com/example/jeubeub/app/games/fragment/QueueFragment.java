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
                    CallGameRequestPopup callGameRequestPopup = new CallGameRequestPopup(getActivity(), getContext());
                    callGameRequestPopup.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request.getRequest(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject json) {
                        try {
                            int queueId = json.getInt("id");

                            //A changer en fonction de la selection des utilisateurs
                            Request.getRequest(null, getContext(), MenuActivity.JEUBEUB_API + "/friend/sendCallGame?playerId=" + LoginActivity.USER_TOKEN + "&playerIdRequest=10&queueId=" + queueId + "&gameName=" + cls.getSimpleName(), null);
                            Request.sendWaitQueueRequest(getContext(), cls, queueId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Exception exception) {
                        System.err.println(exception);
                    }
                }, getContext(), Game.JEUBEUB_API_GAME + "/" + cls.getSimpleName().toLowerCase() + "/createPrivateQueue?playerId=" + LoginActivity.USER_TOKEN, null);
            }
        });
         */
    }
}
