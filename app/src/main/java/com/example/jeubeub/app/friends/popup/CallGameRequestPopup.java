package com.example.jeubeub.app.friends.popup;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.friends.service.FriendService;
import com.example.jeubeub.app.popup.OKCancelPopup;
import com.example.jeubeub.app.popup.Popup;

import org.json.JSONException;

public class CallGameRequestPopup extends OKCancelPopup {

    private FriendService friendService;

    public CallGameRequestPopup(Activity activity, @NonNull Context context) throws JSONException {
        super(context);
        friendService = new FriendService(activity, getContext());
        ListView friendListView = findViewById(R.id.friend_listView);
        friendService.loadFriend(list -> friendListView.setAdapter(friendService.getAdapterOnlyName(list, getLayoutInflater())));
    }

    @Override
    protected void eventOnClickOK() {
        System.out.println(friendService.getListIdPlayerInvited());
    }

    @Override
    protected void eventOnClickCancel() {

    }

    @Override
    public int getContentView() {
        return R.layout.popup_call_game_request;
    }

    @Override
    public void setView() throws JSONException {

    }
}
