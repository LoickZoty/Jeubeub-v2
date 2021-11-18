package com.example.jeubeub.app.service;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.jeubeub.app.model.FriendItem;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendService {

    private Activity activity;
    private Context context;

    public FriendService(Activity activity, Context context){
        this.activity = activity;
        this.context =context;
    }


    public void loadFriend(loadFriendCallBack loadFriendCallBack) {
        Games.getPlayersClient(this.activity, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this.context))).loadFriends(10, false)
                .addOnCompleteListener(data -> {
                    List<FriendItem> friendItemList = new ArrayList<>();
                    friendItemList.add(new FriendItem("Caroline", 1));
                    friendItemList.add(new FriendItem("Gaetan", 2));
                    loadFriendCallBack.onLoad(friendItemList);
                });
    }
}
